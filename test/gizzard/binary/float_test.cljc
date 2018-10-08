(ns gizzard.binary.float-test
  (:require
   [clojure.test :refer [deftest is are testing]]
   [gizzard.binary.byte :as byte]
   [gizzard.binary.float :as float]))


;; Binary Reps From: https://baseconvert.com/ieee-754-floating-point
(def results
  {3.14 [(byte/unsigned 2r01000000)
         (byte/unsigned 2r01001000)
         (byte/unsigned 2r11110101)
         (byte/unsigned 2r11000011)]})


(deftest test-convert-to-bytes
  (is (= (get results 3.14) (float/to-byte-vector 3.14))))
