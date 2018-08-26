(defproject gizzard "0.1.0-SNAPSHOT"
  :description "wat-to-wasm compiler in clojure"
  :url "http://github.com/benzap/gizzard"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [org.clojure/tools.reader "1.3.0"]
                 [org.clojure/tools.cli "0.3.7"]
                 [funcool/cuerdas "2.0.6"]
                 [com.rpl/specter "1.1.1"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-ancient "0.6.15"]
            [lein-doo "0.1.10"]]

  :repositories [["clojars" {:sign-releases false}]]

  :cljsbuild {:builds {:dev
                       {:source-paths ["src" "dev"]
                        :compiler {:output-dir "resources/public/js/compiled/out"
                                   :output-to "resources/public/js/compiled/gizzard.js"
                                   :optimizations :whitespace
                                   :pretty-print true
                                   :source-map "resources/public/js/compiled/gizzard.js.map"}}
                       :prod
                       {:source-paths ["src"]
                        :compiler {:output-to "resources/public/js/compiled/gizzard.min.js"
                                   :optimizations :advanced
                                   :pretty-print false}}
                       :test
                       {:id "test"
                        :source-paths ["src" "test"]
                        :compiler {:output-to "resources/public/js/compiled/test/test-runner.js"
                                   :output-dir "resources/public/js/compiled/test/out"
                                   :main gizzard.test-runner
                                   :target :nodejs
                                   :optimizations :none}}}}

  :doo {:build "test"
        :alias {:default [:node]}}

  :aliases {"project-version" ["run" "-m" "gizzard.utils.version/print-project-version"]}

  :profiles
  {:dev
   {:main gizzard.commandline
    :source-paths ["src" "dev" "test"]
    :dependencies [[org.clojure/tools.namespace "0.2.11"]]
    :repl-options {:init-ns gizzard.dev.user
                   ;;:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]
                   :port 9007}}
                   
   :uberjar
   {:jvm-opts ["-Dclojure.compiler.direct-linking=true"]
    :main gizzard.commandline
    :aot [gizzard.core gizzard.commandline]}})
