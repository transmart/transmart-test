package pages.analyses

import pages.AnalyzePage

/**
 * Created by weymouth on 5/13/15.
 */
class CooralationsAnalysisPage extends AnalyzePage {

    static at = {
        selectedAnalysis == 'Correlation Analysis'
    }

    static content = {
        def parentContent = AnalyzePage.content
        parentContent.delegate = delegate
        parentContent.call()

        analysisWidgetHeader {
            $('div#analysisWidget h2')
        }

        variablesBox { $('div#divVariables') }

        runButton { $('input.runAnalysisBtn') }

        resultOutput { $('div#analysisOutput') }

        analysisHeader { $('div#analysisOutput h2').text() }
    }
}
