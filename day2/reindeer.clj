(ns reindeer)
(require '[clojure.string :as str])

(defn gradual? [report]
  (every?
      (comp 
        (partial >= 3)
        (partial abs) 
        (partial apply -))
      (partition 2 1 report)))

;; tests for STRICT monotonicity
(defn monotonic? [report]
  (or
    (apply > report)
    (apply < report)))

(defn revise [report i]
  (concat
    (take i report)
    (drop (inc i) report)))

(defn safe? [dampened-report]
  (and 
    (monotonic? dampened-report)
    (gradual? dampened-report)))

(defn dampened-safe? [report]
  (loop [i -1] ; initial revision should provide the entire list
    (cond
      (>= i (count report)) false
      (safe? (revise report i)) true
      :else (recur (inc i)))))

(as-> (doall (line-seq (java.io.BufferedReader. *in*))) x
  (map #(str/split % #" ") x)
  (map (partial map #(Integer/parseInt %)) x)
  (map dampened-safe? x)
  (filter identity x)
  (count x)
  (println x))
