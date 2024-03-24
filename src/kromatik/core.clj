(ns kromatik.core)

(defmulti ->ns-map
  (fn [_ x] (coll? x)))

(defn find-nsk [paths k]
  (when-not (empty? paths)
    (let [[path-nsk path-vals] (first paths)]
      (if (contains? path-vals k)
        path-nsk
        (find-nsk (rest paths) k)))))

(defmethod ->ns-map true
  [m paths]
  (let [fmt (fn [k]
              (let [nsk (find-nsk paths k)]
                (keyword (str (name nsk) "/" (name k)))))
        comb (fn [acc [k v]] (assoc acc (fmt k) v))]
    (reduce comb {} m)))

(defmethod ->ns-map false
  [m nsk]
  (let [fmt (fn [k] (keyword (str (name nsk) "/" (name k))))
        comb (fn [acc [k v]] (assoc acc (fmt k) v))]
    (reduce comb {} m)))

(defn ->bare-map [m]
  (let [fmt (fn [k] (-> k name keyword))
        comb (fn [acc [nsk v]] (assoc acc (fmt nsk) v))]
    (reduce comb {} m)))
