(ns reagent-title-demo.core
  (:require [clojure.string :as string]
            [reagent.core :as reagent]
            [goog.dom :as gdom]))

(defn set-title
  [title]
  (set! (.-title (gdom/getDocument)) title))

(def title-class (reagent/create-class
                  {:component-did-mount (fn [d]
                                          (let [[xs] (reagent/children d)]
                                            (set-title xs)))
                   :component-did-update (fn [d]
                                           (let [[xs] (reagent/children d)]
                                             (set-title xs)))
                   :render (fn []
                             [:div])}))

(def app-state (reagent/atom nil))

(defn home-page-component
  []
  (let [t  (if (string/blank? @app-state)
             "Default"
             @app-state)]
    [:div
     [title-class t]
     [:div
      [:form.form-inline
       [:label "Title"]
       [:input.form-control {:type "text"
                             :on-change #(reset! app-state (-> % .-target .-value))}]]]
     [:h2 (str "Title should be: " t)]]))

(defn mount-root
  []
  (reagent/render [home-page-component] (.getElementById js/document "app")))

(defn init!
  []
  (mount-root))
