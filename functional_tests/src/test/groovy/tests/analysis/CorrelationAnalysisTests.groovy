package tests.analysis

import functions.Constants

import org.junit.Test

import pages.AnalyzePage
import pages.analyses.CooralationsAnalysisPage

import tests.CheckLoginPageAbstract

import static org.hamcrest.Matchers.containsInAnyOrder
import static org.hamcrest.Matchers.is

public class CorrelationAnalysisTests extends CheckLoginPageAbstract{

    @Test
    void CorrelationTest() {

        def params = setParams()

        setUpAnalysisSubPage 'Correlation Analysis', params

        runAnalysis params

        verifyPage()
    }

    private void verifyPage() {
        def resultHeader = "Correlation Table (p-values on top right half, correlation coefficient on bottom left)"

        println $('div#analysisOutput h2').size()
    }

    private void runAnalysis(Map params) {

        def v1 = params.variable1
        def v2 = params.variable2
        def v3 = params.variable3
        def v2Array = [v1,v2]
        def v3Array = [v1,v2,v3]

        dragNodeToBox v1, variablesBox
        dragNodeToBox v2, variablesBox, containsInAnyOrder(v2Array.collect { is it as String })
        dragNodeToBox v3, variablesBox, containsInAnyOrder(v3Array.collect { is it as String })

        runButton.click()
        waitFor(8, message: "SurvivalAnalysis RunButton.click() - timed out") { resultOutput } // wait up to 8 seconds for result
    }

    private setUpAnalysisSubPage(String analysisHeader, Map params) {
        goToPageMaybeLogin AnalyzePage

        dragNodeToSubset params.subsetNode, 1, 1

        selectAnalysis analysisHeader
        page CooralationsAnalysisPage
        verifyAt()

        waitFor { analysisWidgetHeader }
    }

    private setParams() {

        String diagnosisKey = "${Constants.GSE8581_KEY}Endpoints\\Diagnosis\\"

        return [
                subsetNode:    Constants.GSE8581_KEY,
                variable1:      "${Constants.GSE8581_KEY}Endpoints\\FEV1\\",
                variable2:      "${Constants.GSE8581_KEY}Endpoints\\Forced Expiratory Volume Ratio\\",
                variable3:      "${Constants.GSE8581_KEY}Subjects\\Age\\"
                ]

    }

}

