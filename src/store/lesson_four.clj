(ns store.lesson-four
  (:require [store.db :as s.db]))

(println (s.db/all-orders))