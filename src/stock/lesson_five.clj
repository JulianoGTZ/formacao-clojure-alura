(ns stock.lesson-five)

;; Setting a map
(def order {:bag {:amount 10 :price 10}})

;; Printing value
(println "Order value" order)

;; Threading example
(println
  (-> order :bag :amount))