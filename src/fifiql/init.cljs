(ns fifiql.init
  (:require
   [reagent.core :as r]
   [re-frame.core :as re]
   [fifiql.ui.events :as ui.events]
   [fifiql.ui.layout :refer [base-layout]]))


(enable-console-print!)


(defn ^:export start []
  (r/render [base-layout] (.querySelector js/document "#app"))
  (re/dispatch-sync [::ui.events/init]))
