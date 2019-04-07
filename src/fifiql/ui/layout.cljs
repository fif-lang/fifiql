(ns fifiql.ui.layout
  (:require
   [re-frame.core :as re]
   [fifiql.ui.events :as ui.events]
   [fifiql.ui.subs :as ui.subs]
   [fifiql.ui.component.sidebar :as sidebar]
   [fifiql.ui.component.editor :as editor]))


(defn base-layout []
  (let [query-sform (re/subscribe [::ui.subs/editor-text])
        toggle-stdlib? (re/subscribe [::ui.subs/toggle-stdlib?])]
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
        [:input.search-bar
         {:type "text"
          :on-change #(re/dispatch [::ui.events/set-search-string (-> % .-target .-value)])}]
        [:div.stdlib-toggle
         {:on-click #(re/dispatch [::ui.events/toggle-stdlib-words])}
         [:span "Show Stdlib Words"]
         [:input {:type "checkbox" :checked @toggle-stdlib?}]]
        [sidebar/search-listing]]
       [:div.editor
        [editor/editor]]
       [:div.sidebar-info
        [sidebar/word-info]]
       [:div.editor-info
        [:div.stack [editor/stack-info]]
        [:div.stdout [editor/stdout-info]]
        [:div.stderr [editor/stderr-info]]]])))
