# kromatik

A Clojure library designed to converts between namespaced map and unnamespaced map.

## Usage

```clojure
(:require
   [kromatik.core :as km])
```

### A single namespace is given for entire map

```clojure
(km/->ns-map {:a 1 :b 2} :hello)
=> #:hello{:a 1, :b 2}
```

### A detailed paths for different namespaces is given

```clojure
(km/->ns-map
    {:a 1 :b 2}
    {:hello #{:a}
     :bye #{:b}})
=> {:hello/a 1, :bye/b 2}

(km/->ns-map
     {:a 1 :b 2}
     {:hello #{:a :x :y :z}
      :bye #{:b}})
=>  {:hello/a 1, :bye/b 2}
```

### turn namespaced map into unnamespaced map

```clojure
(km/->bare-map
    {:xyz/a 1 :zyx/b 2})
=> {:a 1 :b 2}

(km/->bare-map
    {:xyz/a 1 :b 2})
=> {:a 1 :b 2}
```

## License

Copyright © 2024

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
