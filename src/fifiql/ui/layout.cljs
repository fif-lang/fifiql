(ns fifiql.ui.layout
  (:require
   [fifiql.ui.component.sidebar :as sidebar]))


(defn base-layout []
  [:<>
   [:div.sidebar-header
    [:span.main "Fifiql"]
    [:span.small "Interactive Query Evaluator"]]
   [:div.editor-header
    [:div.query-button "Run Query"]]
   [:div.sidebar
    [:input.search-bar {:type "text"}]
    [:div.stdlib-toggle
     [:span "Show Stdlib Words"]
     [:input {:type "checkbox"}]]
    [sidebar/search-listing]]
   [:div.editor
    [:textarea.editor-area]]
   [:div.sidebar-info "sidebar-info"]
   [:div.editor-info
    [:div.stack "stack"]
    [:div.stdout "stdout"]
    [:div.stderr "stderr"]]])
