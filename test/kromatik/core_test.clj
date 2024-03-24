(ns kromatik.core-test
  (:require
   [clojure.test :as t]
   [kromatik.core :as src]))

(t/deftest find-nsk-test
  (t/testing "sought key exists in path vals"
    (t/is
     (= :abc
        (src/find-nsk {:abc #{:a :b}
                       :def #{:c :d}} :a))))
  (t/testing "sought key not exists in path vals"
    (t/is
     (nil?
      (src/find-nsk {:abc #{:a :b}
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
          :bye #{:b}})))))

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
