(ns Part-V.lesson-one
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn add-patient
  [patients patient]
  (if-let [id (:id patient)]
    (assoc patients id patient)
    (throw (ex-info "Patient doesn't have id" {:patient patient}))))

(s/defn print-report
  [visits
   patient :- Long]
  (println "Visits of the patient" patient "are" visits))

(defn add-visit
  [visits patient newVisits]
  (if (contains? visits patient)
    (update visits patient concat newVisits)
    (assoc visits patient newVisits)))

(defn test-patient-use []
  (let [john   {:id 25 :name "John"}
        mary   {:id 25 :name "Mary"}
        jhonny {:id 25 :name "Johnny"}
        patients (reduce add-patient {} [john mary jhonny])
        visits {}]
        (add-visit visits 25 ["01/01/2010"])
        (add-visit visits 25 ["01/01/2010"])
        (add-visit visits 25 ["01/01/2010"])
        (print-report visits 5)))

(test-patient-use)

(s/set-fn-validation! true)

(s/defn simple-symbol [x :- Long]
  (println x))

(s/defn new-patient
  [id :- Long
   name :- s/Str]
 {:id id :name name})

(new-patient 10 "111")
(new-patient "1" 1)

