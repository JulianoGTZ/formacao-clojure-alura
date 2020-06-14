(ns records-and-protocols.model)

(defprotocol Datable
  (to-milliseconds [this]))

(extend-type java.lang.Number
  Datable
  (to-milliseconds [this] this))

(extend-type java.util.Date
  Datable
  (to-milliseconds [this] (.getTime this)))

(extend-type java.util.Calendar
  Datable
  (to-milliseconds [this] (to-milliseconds (.getTime this))))

