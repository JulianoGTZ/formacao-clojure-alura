(ns records-and-protocols.lesson-five
  (:use clojure.pprint))

(defn authorizer-type [order]
  (let [patient    (:patient order)
        situation (:situation patient)]
    (cond (= :urgent situation) :always-authorized
          (contains? patient :plan) :health-plan
          :else :mininum-credit)))

(defmulti should-assign-pre-authorization? authorizer-type)

(defmethod should-assign-pre-authorization? :always-authorized [order] false)

(defmethod should-assign-pre-authorization? :health-plan [order]
  (not (some #(= % (:procedure order)) (:plan (:patient order)))))

(defmethod should-assign-pre-authorization? :mininum-credit [order]
  (>= (:value order 0) 50))

(let [particular {:id 15 :name "John" :birth-date "18/9/1981" :situation  :normal}
      heathPlan  {:id 15 :name "Maria" :birth-date "18/9/1981" :situation :urgent}]
  (pprint (should-assign-pre-authorization? {:patient particular :value 1000 :procedure :blood-collection}))
  (pprint (should-assign-pre-authorization? {:patient heathPlan  :value 40 :procedure :blood-collection})))

