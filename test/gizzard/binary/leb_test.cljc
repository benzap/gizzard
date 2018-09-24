(ns gizzard.binary.leb-test
  (:require
   [clojure.test :refer [deftest is are testing]]
   [gizzard.binary.leb :as leb]
   [gizzard.binary.byte :as byte]))


(def unsigned-results
  {624485 [(byte/unsigned 2r11100101)
           (byte/unsigned 2r10001110)
           (byte/unsigned 2r00100110)]})  


(def signed-results
  {-424091 [(byte/unsigned 2r11100101)
            (byte/unsigned 2r10001110)
            (byte/unsigned 2r01100110)]

   424091 [(byte/unsigned 2r10011011)
           (byte/unsigned 2r11110001)
           (byte/unsigned 2r00011001)]})


(deftest encode-unsigned-long
  (is (= [(byte/unsigned 0)] (leb/encode-unsigned 0)))
  (is (= [(byte/unsigned 1)] (leb/encode-unsigned 1)))
  (is (= (get unsigned-results 624485) (leb/encode-unsigned 624485))))


(deftest encode-signed-long
  (is (= [0] (leb/encode-signed 0)))
  (is (= [(byte/unsigned 2r01111111)] (leb/encode-signed -1)))
  (is (= (get signed-results -424091) (leb/encode-signed -424091)))
  (is (= (get signed-results 424091) (leb/encode-signed 424091))))
