(ns collections.lesson-three
  (:require [store.db :as s.db]))

(defn total-item
  [[_ details]]
  (* (get details :amount 0) (get details :unit-price 0)))

(defn total-order
  [order]
  (reduce + (map total-item order)))

(defn total-order-value
  [orders]
  (->> orders
       (map :items)
       (map total-order)
       (reduce +)))

(defn amount-per-user
  [[user orders]]
  {:user-id           user
   :total-of-orders   (count orders)
   :total-order-value (total-order-value orders)})

;; Get all orders
;; Group by user key
;; Get vals from key-map set
;; Map count function
;; Print the count

(->> (s.db/all-orders)
     (group-by :user)
     (map amount-per-user)
     println)