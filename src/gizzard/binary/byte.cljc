(ns gizzard.binary.byte
  "My own take on byte conversion. `(byte)` doesn't work with unsigneds
  within clojure, and signed values don't work well within
  clojurescript. Includes additional bitwise functions for working
  specifically with bytes.")



(defn signed
  "Signed byte that works between clojure and clojurescript.
  
  Notes:
  
  - Clojure Treats it as a typical signed byte as defined by the the java type Byte.

  - Clojurescript Treats it as a js/Number, so the result is
  bit-converted into a twos-complement representation of a signed
  byte."
  [x]
  (assert (<= x 127) "Integer value cannot be greater than 127")
  (assert (>= x -128) "Integer value cannot be less than -128")
  #?(:clj (byte x)
     :cljs (if (< x 0)
             (byte (- (+ (bit-not (bit-and x 0xFF)) 1)))
             (byte x))))


(defn unsigned
  "Unsigned byte that works between clojure and clojurescript.

  Notes:

  - Clojurescript treats unsigned correctly, but still stores it
  within a js/Number. (can be problematic)

  - Clojure re-uses Java's Byte type, which does not support unsigned
  bytes. We can convert our value to store our unsigned byte correctly
  within the unsigned byte type."
  [x]
  (assert (<= x 255) "Integer value cannot be greater than 255")
  (assert (>= x 0) "Integer value cannot be less than 0")
  #?(:clj (if (> x 127)
            (byte (bit-or -128 (- x 128)))
            (byte x))
     :cljs (byte x)))


(defn shift-right
  "Perform a bit-shift-right on a byte."
  [x n]
  (if-not (> n 7)
    (byte (bit-shift-right (bit-and 0xFF x) n))
    (byte 0)))


(defn shift-left
  "Perform a bit-shift-left on a byte."
  [x n]
  (unsigned (bit-and 0xFF (bit-shift-left x n))))


(defn hex-string
  "Returns a hexidecimal representation of a byte value."
  [x]
  (let [s #?(:clj (Integer/toHexString x)
             :cljs (.toString x 16))]
    (case (count s)
      0 "00"
      1 (str "0" s)
      (subs s (- (count s) 2) (count s)))))


(defn binary-string
  "Returns a binary representation of a byte value."
  [x]
  (let [s #?(:clj (Integer/toBinaryString x)
             :cljs (.toString x 2))
        c (count s)]
    (if (< c 8)
      (str (apply str (repeat (- 8 c) "0")) s)
      (subs s (- (count s) 8) (count s)))))
