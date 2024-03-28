(ns kromatik.core)

(defn combine [k1 k2]
  (keyword (name k1) (name k2)))

(defn pick [paths k]
  (when-not (empty? paths)
    (let [[path-nsk path-vals] (first paths)]
      (if (contains? path-vals k)
        path-nsk
        (pick (rest paths) k)))))

(defmulti ->ns-map
  (fn [_ x] (map? x)))

(defmethod ->ns-map true
  [m paths]
  (let [fmt #(combine (pick paths %) %)]
    (update-keys m fmt)))

(defmethod ->ns-map false
  [m k-ns]
  (update-keys m (partial combine k-ns)))

(defn ->bare-map [m]
  (update-keys m (comp keyword name)))
