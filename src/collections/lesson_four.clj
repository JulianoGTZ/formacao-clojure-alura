(ns collections.lesson-four
  (:require [store.db :as s.db]
            [store.logic :as s.logic]))

;; Examples using Ordered and reverse functions
(let [resume (s.logic/resume-per-user (s.db/all-orders))
      orders (s.db/all-orders)]
  (println resume)
  (println "Ordered" (sort-by :total-price resume))
  (println "Reverse" (reverse (sort-by :total-price resume)))
  (println (first resume))
  (println (count resume))
  (println (get resume 1))
  (println (take 2 resume))
  ;; Some example passing a predicate
  (println "> 500 some =>" (some #(> (:total-price %)) resume))

  ;; Example with get-in
  (println (get-in orders [0 :items :bag :amount])))
