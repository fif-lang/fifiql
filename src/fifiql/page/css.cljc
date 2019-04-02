(ns fifiql.page.css
  (:require
   [garden.core :as garden]
   [garden.color :as color]
   [garden.units :refer [px em]]
   [fifiql.page.css.color :as css.color]
   [fifiql.page.css.font :as css.font]))


(defn generate-css []
  (garden/css
   [:*
    {:box-sizing :border-box
     :margin 0
     :padding 0}]
   
   [:html
    {:width "100vw"
     :height "100vh"}]

   [:body  
    {:width "100vw"
     :height "100vh"
     :font-family css.font/sans-serif
     :color css.color/black
     :background-color css.color/off-white}]

   [:#app
    {:display :grid
     :width "100vw"
     :height "100vh"
     :grid-template-areas "
     'sidebar-header  editor-header'
     'sidebar         editor'
     'sidebar-info    editor-info'
     "
     :grid-template-rows "50px 3fr 1fr"
     :grid-template-columns "400px 1fr"}

    [:.loading
     {:display :flex
      :height "100vh"
      :width "100vw"
      :justify-content :center
      :align-items :center
      :font-size (em 5)}]]

   [:.sidebar-header
    {:display :flex
     :justify-content :space-around
     :align-items :center
     :grid-area :sidebar-header
     :background-color css.color/orange
     :padding (em 0.5)
     :padding-bottom 0}
    [:.main
     {:font-weight "bold"
      :font-size (em 2.0)}]
    [:.small
     {:font-size (em 1.2)}]]

   [:.editor-header
    {:display :grid
     :grid-area :editor-header
     :grid-template-areas "
     '. .'
     '. query-button'
     "
     :grid-template-rows "1fr 1fr"
     :grid-template-columns "1fr 120px"
     :background-color css.color/blue}
    [:.query-button
     {:grid-area :query-button
      :background-color (color/lighten css.color/blue 20)
      :display :flex
      :justify-content :center
      :align-items :center}]]

   [:.sidebar
    {:display :grid
     :grid-area :sidebar
     :background-color css.color/orange
     :grid-template-areas "
     'search-bar     stdlib-toggle'
     'search-listing search-listing'
     "
     :grid-template-rows "2em 1fr"
     :padding (em 0.5)}

    [:.search-bar
     {:grid-area :search-bar
      :padding (em 0.3)}]

    [:.stdlib-toggle
     {:grid-area :stdlib-toggle
      :display :flex
      :justify-content :center
      :align-items :center}
     [:input
      {:margin (em 0.3)}]]

    [:.search-listing
     {:grid-area :search-listing
      :background-color (color/lighten css.color/orange 10)}]]

   [:.editor
    {:display :grid
     :grid-area :editor
     :grid-template-areas "'fill'"
     :grid-template-rows "1fr"
     :grid-template-columns "1fr"
     :background-color css.color/green}
    [:.editor-area
     {:grid-area :fill
      :font-family css.font/monospace
      :margin (em 0.3)
      :font-size (em 1.5)
      :outline :none
      :resize :none
      :border :none}]]

   [:.sidebar-info
    {:grid-area :sidebar-info
     :background-color (color/lighten css.color/orange 20)}]

   [:.editor-info
    {:display :grid
     :grid-template-areas "
     'stack stdout'
     'stack stderr'
     "
     :grid-template-columns "1fr 1fr"
     :grid-template-rows "1fr 1fr"
     :grid-area :editor-info
     :background-color (color/lighten css.color/green 20)}
    [:.stack
     {:grid-area :stack
      :border-right-width (em 0.5)
      :border-right-style :solid
      :border-right-color css.color/green}]
    [:.stdout
     {:grid-area :stdout
      :background-color (color/lighten css.color/blue 35)}]
    [:.stderr
     {:grid-area :stderr
      :background-color (color/lighten css.color/orange 30)}]]))
