package tests.SmartRTests

import functions.Constants
import org.junit.Test
import pages.AnalyzePage
import pages.SmartR.HeatmapPage
import tests.CheckLoginPageAbstract

import geb.report.*
import org.openqa.selenium.JavascriptExecutor

class HeatmapTests extends CheckLoginPageAbstract {

    @Test
    void heatmapTest() {
        def nodes = [
                cohort1: Constants.GSE4382_KEY + 'Subjects\\Vital Status Details\\Alive, Clinical disease or syndrome present\\',
                cohort2: Constants.GSE4382_KEY + 'Subjects\\Vital Status Details\\Cause of Death, Other\\',
                highDimNode: Constants.GSE4382_KEY + 'Biomarker Data\\Gene Expression\\Custom Array Stanford Microarray Database\\Breast\\'
        ]
        go baseUrl
        to AnalyzePage
        dragNodeToSubset nodes.cohort1, 1, 1
        dragNodeToSubset nodes.cohort2, 2, 1
        selectSmartRWorkflow 'heatmap'
        page HeatmapPage
        verifyAt()
        waitFor { analysisContainer }
        dragNodeToBox nodes.highDimNode, highDimBox
        fetchDataButton.click()
        waitFor(40) { summaryOutputImg }
        js.exec 'jQuery("div#heim-fetch-output").get(0).scrollIntoView()'
        assert compareCurrentViewWith('heatmapSummary.png')
        runTab.click()
        waitFor { runButton }
        runButton.click()
        waitFor(20) { snapshotButton.@disabled != 'true' }
        js.exec 'jQuery("rect.extraSquare").get(0).scrollIntoView()'
        assert compareCurrentViewWith('heatmap.png')
    }
}
