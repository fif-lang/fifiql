(ns fifiql.core
  (:require
   [reagent.core :as r]
   [fifiql.ui.layout :refer [base-layout]]))


(enable-console-print!)


(r/render [base-layout] (.querySelector js/document "#app"))
