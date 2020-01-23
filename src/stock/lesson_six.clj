(ns stock.lesson-six)

;; Setting a map
(def order {:bag { :amount 100 :price 10 }})

;; Declaring the functions relative to order

(defn price-product [value]
  (* (:amount value) (:price value)))

(defn total-order-amount
  [order]
  (->> order
       vals
      (map price-product)
      (reduce +)))

;; Function call
(println "Total order amount" (total-order-amount order))