(ns hospital.logic)

(defn hospital-is-full?
  [hospital department]
  (-> hospital
      (get department)
      count
      (>= 5)))

(defn arrives-in
  [hospital department people]
  (if (not (hospital-is-full? hospital department))
    (update hospital department conj people)
    (throw (ex-info "Hospital is full" {:try-add-people people}))))

(defn attend
  [hospital department]
  (update hospital department pop))