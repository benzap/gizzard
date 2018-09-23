(ns gizzard.binary.byte-test
  (:require
   [clojure.test :refer [deftest is are testing]]
   [gizzard.binary.byte :as byte]))


(deftest test-unsigned
  (is (= (byte/unsigned 255) (byte -1)))
  (is (= (byte/unsigned 0) (byte 0)))
  (is (= (byte/unsigned 128) (byte -128)))
  (is (= (byte/unsigned 129) (byte -127))))


(deftest test-shift-right
  (is (= (byte/shift-right 2r10000000 1) 2r01000000))
  (is (= (byte/shift-right (byte/unsigned 128) 1) 64))
  (is (= (byte/shift-right (byte/unsigned 128) 2) 32))
  (is (= (byte/shift-right (byte/unsigned 128) 3) 16))
  (is (= (byte/shift-right (byte/unsigned 128) 4) 8))
  (is (= (byte/shift-right (byte/unsigned 128) 5) 4))
  (is (= (byte/shift-right (byte/unsigned 128) 6) 2))
  (is (= (byte/shift-right (byte/unsigned 128) 7) 1))
  (is (= (byte/shift-right (byte/unsigned 128) 8) 0))
  (is (= (byte/shift-right (byte/unsigned 128) 99) 0))
  (is (= (byte/shift-right (byte/unsigned 255) 1) 127))
  (is (= (byte/shift-right (byte/unsigned 255) 2) 63))
  (is (= (byte/shift-right (byte/unsigned 255) 99) 0))
  (is (= (byte/shift-right (byte/unsigned 0) 1) 0)))


(deftest test-shift-left
  (is (= (byte/shift-left (byte/unsigned 1) 1) 2))
  (is (= (byte/shift-left (byte/unsigned 1) 2) 4))
  (is (= (byte/shift-left (byte/unsigned 1) 3) 8))
  (is (= (byte/shift-left (byte/unsigned 1) 4) 16))
  (is (= (byte/shift-left (byte/unsigned 1) 5) 32))
  (is (= (byte/shift-left (byte/unsigned 1) 6) 64))
  (is (= (byte/shift-left (byte/unsigned 1) 7) (byte/unsigned 128)))
  (is (= (byte/shift-left (byte/unsigned 1) 8) 0)))


(deftest test-hex-string
  (is (= (byte/hex-string 255) "ff"))
  (is (= (byte/hex-string (byte/unsigned 255)) "ff"))
  (is (= (byte/hex-string (byte/unsigned 0)) "00"))
  (is (= (byte/hex-string (byte/unsigned 1)) "01")))


(deftest test-binary-string
  (is (= (byte/binary-string 1) "00000001"))
  (is (= (byte/binary-string 4) "00000100"))
  (is (= (byte/binary-string 255) "11111111"))
  (is (= (byte/binary-string (byte/unsigned 255)) "11111111")))
