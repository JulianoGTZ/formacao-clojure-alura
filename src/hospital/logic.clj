(ns hospital.logic
  (:require [hospital.model :as h.model]
            [schema.core :as s]))

(defn full-hospital?
  [hospital department]
  (some-> hospital
          department
          count
          (>= 5)))

(defn arrives-in
  [hospital department people]
  (if (not (full-hospital? hospital department))
    (update hospital department conj people)
    (throw (ex-info "Hospital is full" {:try-add-people people}))))

(defn arrives-in-with-delay
  [hospital department people]
  (Thread/sleep (* (rand) 2000))
  (if (not (full-hospital? hospital department))
    (do
      (update hospital department conj people))
    (throw (ex-info "Queue is fully already" {:try-add-people people}))))

(defn arrives-in-with-delay-and-log
  [hospital department people]
  (Thread/sleep (* (rand) 2000))
  (if (not (full-hospital? hospital department))
    (do
      (update hospital department conj people))
    (throw (ex-info "Queue is fully already" {:try-add-people people}))))

(s/defn attend :- h.model/Hospital
  [hospital :- h.model/Hospital
   department :- s/Keyword]
  (update hospital department pop))

(defn getNextPerson
  "Return the next person in the line"
  [hospital department]
  (-> hospital
      department
      peek))

(s/defn transfer :- h.model/Hospital
  "Transfer the next patient from a line to another"
  [hospital :- h.model/Hospital
   from-department :- s/Keyword
   to-department :- s/Keyword]
  (let [people (getNextPerson hospital from-department)]
    (-> hospital
        (attend from-department)
        (arrives-in to-department people))))

(defn attend-and-return-person
  [hospital department]
  {:patient  (update hospital department peek)
   :hospital (update hospital department pop)})

(defn attend-and-return-person-with-both-approachs
  [hospital department]
  (let [queue            (get hospital department)
        peek-pop         (juxt peek pop)
        [person updated-queue] (peek-pop queue)
        updated-hospital (update hospital assoc department updated-queue)]
    {:patient  person
     :hospital updated-hospital}))
