(defproject voice-recognition-identity "0.1.0-SNAPSHOT"

  :description "FIXME: write description"
  :url "http://example.com/FIXME"

  :dependencies [[buddy "2.0.0"]
                 [org.flatland/ordered "1.5.7"]
                 [clojure.java-time "0.3.2"]
                 [com.fasterxml.jackson.core/jackson-core "2.9.7"]
                 [com.fasterxml.jackson.datatype/jackson-datatype-jdk8 "2.9.7"]
                 [compojure "1.6.1"]
                 [conman "0.8.3"]
                 [cprop "0.1.13"]
                 [funcool/struct "1.3.0"]
                 [luminus-immutant "0.2.4"]
                 [luminus-migrations "0.6.3"]
                 [luminus-transit "0.1.1"]
                 [luminus/ring-ttl-session "0.3.2"]
                 [markdown-clj "1.0.5"]
                 [metosin/compojure-api "1.1.12"]
                 [metosin/muuntaja "0.6.1"]
                 [metosin/ring-http-response "0.9.1"]
                 [mount "0.1.14"]
                 [nrepl "0.4.5"]
                 [org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.cli "0.4.1"]
                 [org.clojure/tools.logging "0.4.1"]
                 [org.postgresql/postgresql "42.2.5"]
                 [org.webjars.bower/tether "1.4.4"]
                 [org.webjars/bootstrap "4.1.3"]
                 [org.webjars/font-awesome "5.5.0"]
                 [org.webjars/jquery "3.3.1-1"]
                 [org.webjars/webjars-locator "0.34"]
                 [ring-webjars "0.2.0"]
                 [ring/ring-core "1.7.1"]
                 [ring-cors "0.1.7"]
                 [ring/ring-defaults "0.3.2"]
                 [selmer "1.12.5"]
                 [lein-watch "0.0.2"]
                 [korma "0.4.3"]
                 [org.postgresql/postgresql "42.1.4"]]
  :min-lein-version "2.0.0"

  :source-paths ["src/clj"]
  :test-paths ["test/clj"]
  :resource-paths ["resources"]
  :target-path "target/%s/"
  :main ^:skip-aot voice-recognition-identity.core

  :plugins [[lein-immutant "2.1.0"]
            [lein-kibit "0.1.2"]]

  :profiles
  {:uberjar       {:omit-source    true
                   :aot            :all
                   :uberjar-name   "voice-recognition-identity.jar"
                   :source-paths   ["env/prod/clj"]
                   :resource-paths ["env/prod/resources"]}

   :dev           [:project/dev :profiles/dev]
   :test          [:project/dev :project/test :profiles/test]

   :project/dev   {:jvm-opts       ["-Dconf=dev-config.edn"]
                   :dependencies   [[expound "0.7.1"]
                                    [pjstadig/humane-test-output "0.9.0"]
                                    [prone "1.6.1"]
                                    [ring/ring-devel "1.7.1"]
                                    [ring/ring-mock "0.3.2"]]
                   :plugins        [[com.jakemccrary/lein-test-refresh "0.23.0"]]

                   :source-paths   ["env/dev/clj"]
                   :resource-paths ["env/dev/resources"]
                   :repl-options   {:init-ns user}
                   :injections     [(require 'pjstadig.humane-test-output)
                                    (pjstadig.humane-test-output/activate!)]}
   :project/test  {:jvm-opts       ["-Dconf=test-config.edn"]
                   :resource-paths ["env/test/resources"]}
   :profiles/dev  {}
   :profiles/test {}}

  :watch {
          :rate     500
          :watchers {
                     :compile {
                               :watch-dirs    ["src"]
                               :file-patterns [#"\.clj"]
                               :tasks         ["compile"]}}}
  )
