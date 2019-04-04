(ns fifiql.ui.layout
  (:require
   [re-frame.core :as re]
   [fifiql.ui.events :as ui.events]
   [fifiql.ui.subs :as ui.subs]
   [fifiql.ui.component.sidebar :as sidebar]
   [fifiql.ui.component.editor :as editor]))


(defn base-layout []
  (let [query-sform (re/subscribe [::ui.subs/editor-text])]
    (fn []
      [:<>
       [:div.sidebar-header
        [:span.main "Fifiql"]
        [:span.small "Interactive Query Evaluator"]]
       [:div.editor-header
        [:div.query-button
         {:on-click #(re/dispatch [::ui.events/run-query @query-sform])}
         "Run Query"]]
       [:div.sidebar
        [:input.search-bar {:type "text"}]
        [:div.stdlib-toggle
         [:span "Show Stdlib Words"]
         [:input {:type "checkbox"}]]
        [sidebar/search-listing]]
       [:div.editor
        [editor/editor]]
       [:div.sidebar-info
        [sidebar/word-info]]
       [:div.editor-info
        [:div.stack "stack"]
        [:div.stdout "stdout"]
        [:div.stderr "stderr"]]])))
