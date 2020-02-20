(ns store.logic)

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
  {
   :user-id           user
   :total-of-orders   (count orders)
   :total-order-value (total-order-value orders)
   })

(defn resume-per-user [orders]
  (->> orders
       (group-by :user)
       (map amount-per-user)))




