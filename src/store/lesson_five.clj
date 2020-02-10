(ns store.lesson-five
  (:require [store.logic :as s.logic]
            [store.db :as s.db]))

(defn spent-a-lot? [user-info]
  (> (:total-order-value user-info) 500))

(let [orders (s.db/all-orders)
      resume (s.logic/resume-per-user orders)]
  (println (keep spent-a-lot? resume)))

(defn just-print [x]
  (println "just-print" x)
  x)

(println (map just-print (range 10)))

(defn just-print-again [x]
  (println "just-print-again" x)
  x)

(println (map just-print-again (map just-print (range 10))))

;; checking the lazy/eager behavior on map
(->> (range10)
     (map just-print)
     (map just-print-again)
     (println))
