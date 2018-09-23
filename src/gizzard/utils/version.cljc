(ns gizzard.utils.version
  #?(:clj (:import java.lang.System)))


(defn get-project-version []
  #?(:clj (java.lang.System/getProperty "gizzard.version")))


(defn print-project-version []
  (println (get-project-version)))
