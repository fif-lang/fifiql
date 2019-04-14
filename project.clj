(defproject fif-lang/fifiql "1.4.0-SNAPSHOT"
  :description "Fif Query Language Interactive Development Page"
  :url "http://github.com/fif-lang/fifiql"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.520" :scope "provided"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.6"]
                 [garden "1.3.6"]
                 [hiccup "1.0.5"]
                 [hiccups "0.3.0"]

                 ;; fif libraries
                 [fif-lang/fif      "1.4.0"]
                 [fif-lang/fifql    "1.4.0"]
                 [fif-lang/fifql-fx "1.4.0"]]

  :npm {:dependencies [[body-parser "1.18.3"]]
        :devDependencies [[express "4.16.4"]]}

  :repositories [["clojars" {:sign-releases false}]]
  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :source-paths ["src"]

  :uberjar-name "fifiql-server.jar"

  :cljsbuild {:builds
              [{:id "fifiql"
                :source-paths ["src" "dev"]
                :figwheel true
                :compiler {:main fifiql.core
                           :asset-path "/js/compiled/out"
                           :output-to "resources/public/js/compiled/fifiql.js"
                           :output-dir "resources/public/js/compiled/out"
                           :optimizations :none
                           :source-map-timestamp true}}
               {:id "dev-server"
                :source-paths ["src" "dev"]
                :figwheel true
                :compiler {:main fifiql.dev.server
                           :output-to "target/compiled/dev-server.js"
                           :output-dir "target/compiled/out"
                           :optimizations :none
                           :target :nodejs
                           :source-map-timestamp true}}]}


  :profiles 
  {:dev 
   {:main fifiql.dev.server
    :source-paths ["src" "dev"]
    :dependencies [[org.clojure/core.async "0.4.490"]
                   [mount "0.1.16"]
                   [ring/ring-defaults "0.3.2"]
                   [compojure "1.6.1"]
                   [http-kit "2.3.0"]]
    :plugins [[lein-cljsbuild "1.1.7"]
              [lein-figwheel "0.5.18"]
              [lein-npm "0.6.2"]
              [lein-ancient "0.6.15"]]
    :repl-options {:init-ns fifiql.dev.user
                   :port 9005}}})
