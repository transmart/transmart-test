package pages.SmartR

import pages.AnalyzePage

class HeatmapPage extends AnalyzePage {
    static at = {
        smartRWorkflowDropdown.value() == 'heatmap'
    }

    static content = {
        def parentContent = AnalyzePage.content
        parentContent.delegate = delegate
        parentContent.call()

        highDimBox { $('div#heim-high-dim-var') }
        numericalBox { $('div#heim-num-var') }
        categoricalBox { $('div#heim-categ-var') }
        analysisContainer { $('div.heim-analysis-container') }
        fetchDataButton { $('button#heim-btn-fetch-data') }
        summaryOutput { $('div#heim-fetch-output') }
        summaryOutputImg { $('div#heim-fetch-output img') }
        runTab { $('a.ui-tabs-anchor span').find { it.text() == 'Run' } }
        runButton { $('button#heim-btn-run-heatmap') }
        snapshotButton { $('button#heim-btn-snapshot-image') }
    }
}
