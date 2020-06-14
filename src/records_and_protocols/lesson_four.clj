(ns records-and-protocols.lesson-four
  (:use clojure.pprint))

(defrecord PatientWithoutHealthPlan [id name birth-date situation])
(defrecord PatientWithHealthPlan [id name birth-date situation plan])

(defn authorizer-type [order]
  (let [patient    (:patient order)
        situation (:situation patient)
        urgent?   (= :urgent situation)]
    (if urgent?
      :always-authorized
      (class patient))))

(defmulti should-assign-pre-authorization-of-the-order? authorizer-type)

(defmethod should-assign-pre-authorization-of-the-order? :always-authorized [order] false)

(defmethod should-assign-pre-authorization-of-the-order? PatientWithoutHealthPlan [order]
  (>= (:value order 0) 50))

(defmethod should-assign-pre-authorization-of-the-order? PatientWithHealthPlan [order]
  (not (some #(= % (:procedure order)) (:plan (:patient order)))))

(let [particular (->PatientWithoutHealthPlan 15 "John" "18/9/1981" :urgent)
      heathPlan  (->PatientWithHealthPlan 25 "Marry" "21/05/1966"  :urgent [:x-ray :ultrasound-scan])]
  (pprint (should-assign-pre-authorization-of-the-order? {:patient particular :value 1000 :procedure :blood-collection}))
  (pprint (should-assign-pre-authorization-of-the-order? {:patient heathPlan  :value 40 :procedure :blood-collection})))

