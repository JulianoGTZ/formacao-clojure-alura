(ns records-and-protocols.lesson-one
  (:use clojure.pprint))

(defn add-patient
  [patients patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient doesn't have an id" {:patient patient}))))

(defn test-add-patients
  []
  (let [patients {}
        john {:id 15 :name "John" :birth-date "18/09/1981"}
        marry {:id 20 :name "Marry" :birth-date "18/09/1982"}
        paul {:Name "Paul" :birth-date "18/10/1983"}]
    (pprint (add-patient patients john))
    (pprint (add-patient patients marry))
    (pprint (add-patient patients paul))))

#_(test-add-patients)

(defrecord Patient [id name date-birth])

(println (->Patient 15 "John" "18/05/2020"))
(println (->Patient 15 "John" "31/05/2020"))
(println (->Patient 15 "John" "16/05/2020"))
(println (->Patient 15 "John" "28/05/2020"))

(pprint (map->Patient {:id 15 :name "John" :birth-date "09/07/1992"}))
(pprint (class (assoc (Patient. nil "Terry" "18/09/1981") :id 40)))