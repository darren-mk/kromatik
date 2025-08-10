(ns kromatik.core
  #?(:cljs (:refer-clojure :exclude [update-keys])))

#?(:cljs
   (defn update-keys [m f]
     (into {}
           (map (fn [[k v]]
                  [(f k) v])
                m))))

(defn combine [k1 k2]
  (if k1
    (keyword (name k1) (name k2))
    k2))

(defn pick [paths k]
  (when-not (empty? paths)
    (let [[path-nsk path-vals] (first paths)]
      (if (contains? path-vals k)
        path-nsk
        (pick (rest paths) k)))))

(defn convert-by-multi-targets [m paths]
  (let [fmt #(combine (pick paths %) %)]
    (update-keys m fmt)))

(defn convert-by-single-target [m k-ns]
  (update-keys m (partial combine k-ns)))

(defn ->ns-map [m x]
  (let [f (cond (map? x) convert-by-multi-targets
                (keyword? x) convert-by-single-target
                :else (constantly nil))]
    (f m x)))

(defn ->bare-map [m]
  (update-keys m (comp keyword name)))
