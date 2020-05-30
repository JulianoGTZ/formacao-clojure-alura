(ns hospital.logic)

(defn full-hospital?
  [hospital department]
  (-> hospital
      (get department)
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
  (println "Trying to add the people" people)
  (Thread/sleep (* (rand) 2000))
  (if (not (full-hospital? hospital department))
    (do
      (println "Updating the map" people)
      (update hospital department conj people))
    (throw (ex-info "Queue is fully already" {:try-add-people people}))))

(defn attend
  [hospital department]
  (update hospital department pop))