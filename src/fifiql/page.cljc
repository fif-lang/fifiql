(ns fifiql.page
  #?(:cljs (:require-macros [hiccups.core :as hiccups :refer [html]]))
  (:require
   [fifiql.page.css :refer [generate-css]]
   #?(:clj [hiccup.core :refer [html]]
      :cljs [hiccups.runtime])))


(defn main [fifiql-source-path]
  (html
   [:html
    [:head
     [:title "Fifiql - Interactive Query Evaluator"]
     [:meta {:charset "UTF-8"}]
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:link {:href "https://fonts.googleapis.com/css?family=Inconsolata|Merriweather|Open+Sans" :rel "stylesheet"}]
     [:style (generate-css)]]
    [:body
     [:div#app
      [:div.loading
       [:span "Loading..."]]]
     [:script {:type "text/javascript" :src fifiql-source-path}]
     [:script {:type "text/javascript"} "fifiql.init.start();"]]]))
