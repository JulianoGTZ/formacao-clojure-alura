(ns stock.lesson-six)

;; Setting a map
(def order {:bag {:amount 100 :price 10}}
  {:t-shirt {:amount 2 :price 0}})

;; Declaring the functions relative to order

(defn price-product [value]
  (* (:amount value) (:price value)))

(defn total-order-amount
  [order]
  (->> order
       vals
       (map price-product)
       (reduce +)))

(defn free?
  [item]
  (<= (get item :price 0) 0))

;; Calculating the order amount

(println "Total order amount" (total-order-amount order))