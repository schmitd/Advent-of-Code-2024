(ns reindeer)
(require '[clojure.string :as str])

(defn safe [report]
  (and 
    (or 
      (apply > report)
      (apply < report))
    (every?
      (comp
        (partition 2 1)
        (map -)  
        (map abs) 
        (map <= 3))
      report)))

(as-> (doall (line-seq (java.io.BufferedReader. *in*))) x
  (map #(str/split % #" ") x)
  (map (partial map #(Integer/parseInt %)) x)
  ;(prn x) XXX
  (map safe x)  
  (filter identity x)
  (count x)
  (println x))
