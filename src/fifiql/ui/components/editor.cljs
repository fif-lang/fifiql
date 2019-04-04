(ns fifiql.ui.component.editor
  (:require
   [re-frame.core :as re]
   [reagent.core :as r]
   [fifiql.ui.subs :as ui.subs]
   [fifiql.ui.events :as ui.events]))


(defn editor []
  [:textarea.editor-area
   {:on-change #(re/dispatch [::ui.events/change-editor-text (-> % .-target .-value)])}])
