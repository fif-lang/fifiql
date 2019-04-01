(ns fifiql.page
  #?(:cljs (:require-macros [hiccups.core :as hiccups :refer [html]]))
  (:require
   [garden.core :refer [css]]
   #?(:clj [hiccup.core :refer [html]])))


(defn generate-main-css []
  (css
   [:*
    {:box-sizing :border-box
     :margin 0
     :padding 0}]))


(defn main []
  (html
   [:html
    [:head
     [:title "Fifiql - Interactive Query Evaluator"]
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:style (generate-main-css)]]
    [:body
     [:div#app
      [:span "Loading..."]
      [:script {:src "/js/compiled/fifiql.js"}]]]]))
