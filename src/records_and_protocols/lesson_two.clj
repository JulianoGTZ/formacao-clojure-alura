(ns records-and-protocols.lesson-two
  (:use clojure.pprint))

(defrecord Patient [id name birth-date])

(defrecord PatientWithoutHealthPlan [id name birth-date])
(defrecord PatientWithHealthPlan    [id name birth-date plan])

(defprotocol Billable
  (should-assign-pre-authorization? [patient procedure value]))

(extend-type PatientWithoutHealthPlan
  Billable
  (should-assign-pre-authorization? [patient procedure value]
    (>= value 50)))

(extend-type PatientWithHealthPlan
  Billable
  (should-assign-pre-authorization? [patient procedure value]
    (let [plan (:plan patient)]
      (not (some #(= % procedure) plan)))))

(let [particular (->PatientWithoutHealthPlan 15 "John" "18/9/1981")
      heathPlan  (->PatientWithHealthPlan 25 "Marry" "21/05/1966" [:x-ray :ultrasound-scan])]
  (pprint (should-assign-pre-authorization? particular :x-ray 400))
  (pprint (should-assign-pre-authorization? particular :x-ray 400))
  (pprint (should-assign-pre-authorization? heathPlan  :x-raay 400)))

(defprotocol Datable
  (to-milliseconds [this]))

(extend-type java.lang.Number
  Datable
  (to-milliseconds [this] this))

(extend-type java.util.Date
  Datable
  (to-milliseconds [this] (.getTime this)))

(pprint (to-milliseconds (java.util.Date.)))

(extend-type java.util.Calendar
  Datable
  (to-milliseconds [this] (to-milliseconds (.getTime this))))

(pprint (to-milliseconds (java.util.GregorianCalendar.)))


