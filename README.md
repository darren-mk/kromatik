# kromatik

A lightweight, convenient library for converting Clojure maps between namespaced and unnamespaced maps.

## Usage

```clojure
(require '[kromatik.core :as km])
```

### Convert unnamespaced map to namespaced map (single namespace)

```clojure
(km/->ns-map {:a 1 :b 2} :hello)
;; => #:hello{:a 1, :b 2}
```

### Convert unnamespaced map to namespaced map (multiple namespaces)

```clojure
(km/->ns-map {:a 1 :b 2}
             {:hello #{:a}
              :bye #{:b}})
;; => {:hello/a 1, :bye/b 2}
```

### Convert namespaced map to unnamespaced map

```clojure
(km/->bare-map {:xyz/a 1 :zyx/b 2})
;; => {:a 1 :b 2}

(km/->bare-map {:xyz/a 1 :b 2})
;; => {:a 1 :b 2}
```

## License

MIT License © 2025 Darren Kim