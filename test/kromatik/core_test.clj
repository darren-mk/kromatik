(ns kromatik.core-test
  (:require
   [clojure.test :as t]
   [kromatik.core :as src]))

(t/deftest combine-test
  (t/testing "both are keywords"
    (t/is
     (= :abc/def (src/combine :abc :def))))
  (t/testing "k1 is nil"
    (t/is
     (= :def (src/combine nil :def)))))

(t/deftest pick-test
  (t/testing "sought key exists in path vals"
    (t/is
     (= :abc
        (src/pick {:abc #{:a :b}
                   :def #{:c :d}} :a))))
  (t/testing "sought key not exists in path vals"
    (t/is
     (nil?
      (src/pick {:abc #{:a :b}
                 :def #{:c :d}} :y)))))

(t/deftest ->ns-map-test
  (t/testing "single ns key is given"
    (t/is
     (= #:hello{:a 1, :b 2}
        (src/->ns-map {:a 1 :b 2} :hello))))
  (t/testing "correct detailed paths for ns key is given"
    (t/is
     (= {:hello/a 1, :bye/b 2}
        (src/->ns-map
         {:a 1 :b 2}
         {:hello #{:a}
          :bye #{:b}}))))
  (t/testing "correct detailed but redundant paths is given"
    (t/is
     (= {:hello/a 1, :bye/b 2}
        (src/->ns-map
         {:a 1 :b 2}
         {:hello #{:a :x :y :z}
          :bye #{:b}}))))
   (t/testing "correct detailed but some missing"
    (t/is
     (= {:hello/a 1, :bye/b 2 :c 3}
        (src/->ns-map
         {:a 1 :b 2 :c 3}
         {:hello #{:a :x :y :z}
          :bye #{:b}}))))
  (t/testing "unexpected target value is given"
    (t/is
     (nil? (src/->ns-map
            {:a 1 :b 2 :c 3} [1 2 3])))))

(t/deftest ->bare-map-test
  (t/testing "all given keys are namespace"
    (t/is
     (= {:a 1 :b 2}
        (src/->bare-map
         {:xyz/a 1 :zyx/b 2}))))
  (t/testing "some given keys are namespace"
    (t/is
     (= {:a 1 :b 2}
        (src/->bare-map
         {:xyz/a 1 :b 2})))))
