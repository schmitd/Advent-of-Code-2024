(ns reindeer)
(require '[clojure.string :as str])

(defn safe [report]
  (and 
    (or 
      (reduce > report)
      (reduce < report))
    (every?
      (comp
        (partition 2 1)
        (map -)  
        (map abs) 
        (map <= 3))
      report)))

(as-> (line-seq (java.io.BufferedReader. *in*) x
  (map (split #" ") x)
  (safe)  
  (filter identity)
  (count)
  (println))
