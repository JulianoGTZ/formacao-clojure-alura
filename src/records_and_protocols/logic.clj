(ns records-and-protocols.logic
  (:require [records-and-protocols.model :as r.model]))

(defn now
  []
  (r.model/to-milliseconds (java.util.Date.)))