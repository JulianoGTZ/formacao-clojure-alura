(ns stock.lesson-six)

;; Setting a map
(def order {:bag { :amount 10 :price 10 }})

;; Declaring a function

(defn price-product [[_ value]]
  (* (:amount value) (:price value)))

(defn total-order [order]
  (reduce + (map price-product order) ))

;; Function call
(println "Total order amount" (total-order order))