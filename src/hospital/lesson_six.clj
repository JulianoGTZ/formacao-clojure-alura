(ns hospital.lesson-six
  (:use     [clojure pprint])
  (:require [hospital.model :as h.model]))

(defn fit_in_the_queue?
  [queue]
  (-> queue
      count
      (< 5)))

(defn arrives-in
  [line person]
  (if (fit_in_the_queue? line)
    (conj line person)
    (throw (ex-info "Queue is already full" {:trying-add person}))))

(defn arrives-in-by-refset-function!
  [hospital person]
  (let [line (get hospital :queue)]
    (ref-set line (arrives-in (deref line) person))))

(defn arrives-in-by-alter-function!
  [hospital person]
  (let [line (get hospital :queue)]
    (alter line arrives-in person)))

(defn simulate-one-day
  []
  (let [hospital {:queue        (ref h.model/empty_queue)
                  :laboratory-1 (ref h.model/empty_queue)
                  :laboratory-2 (ref h.model/empty_queue)
                  :laboratory-3 (ref h.model/empty_queue)}]
    (dosync
     (arrives-in-by-alter-function! hospital "Little John")
     (arrives-in-by-alter-function! hospital "Little Mathis")
     (arrives-in-by-alter-function! hospital "Little Jeremias")
     (arrives-in-by-alter-function! hospital "Little Malaquias")
     (arrives-in-by-alter-function! hospital "Little Marco")
     (arrives-in-by-alter-function! hospital "Little George"))
    (pprint hospital)))

(defn async-arrives-in!
  [hospital person]
  (future
    (Thread/sleep (rand 5000))
    (dosync
     (println "Trying to add this people" person)
     (arrives-in-by-alter-function! hospital person))))

(defn simulate-one-day-async!
  []
  (let [hospital {:queue        (ref h.model/empty_queue)
                  :laboratory-1 (ref h.model/empty_queue)
                  :laboratory-2 (ref h.model/empty_queue)
                  :laboratory-3 (ref h.model/empty_queue)}]
    (def futures (mapv #(async-arrives-in! hospital %) (range 10)))
    (future
      (dotimes [n 4]
        (Thread/sleep 2000)
        (pprint hospital)
        (pprint futures)))))

(simulate-one-day-async!)