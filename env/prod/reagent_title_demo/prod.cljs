(ns reagent-title-demo.prod
  (:require [reagent-title-demo.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
