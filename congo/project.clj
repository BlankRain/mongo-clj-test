(defproject congo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.novemberain/monger "3.1.0"]
                 [com.taoensso/tufte "1.1.1"]
                 [org.clojure/data.generators "0.1.2"]]
  :main ^:skip-aot congo.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
