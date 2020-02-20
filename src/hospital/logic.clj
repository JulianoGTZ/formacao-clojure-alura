(ns hospital.logic)

(defn arrives-in
  [hospital departament people]
  (update hospital departament conj people))

(defn attend
  [hospital departament]
  (update hospital departament pop))