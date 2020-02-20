(ns hospital.logic)

(defn arrives-in
  [hospital department people]
  (update hospital department conj people))

(defn attend
  [hospital department]
  (update hospital department pop))