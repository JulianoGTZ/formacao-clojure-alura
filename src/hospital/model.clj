(ns hospital.model)

(def empty_queue clojure.lang.PersistentQueue/EMPTY)

(defn new-hospital []
  {
   :queue        empty_queue
   :laboratory-1 empty_queue
   :laboratory-2 empty_queue
   :laboratory-3 empty_queue
   })