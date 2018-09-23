(ns gizzard.binary.byte
  "My own take on byte conversion. `(byte)` doesn't work with
  unsigneds. Includes additional functions for working with unsigned
  bytes.")


#?(:clj
   (def signed byte)
   :cljs
   (defn signed [x]
     (assert (<= x 127) "Integer value cannot be greater than 127")
     (assert (>= x -128) "Integer value cannot be less than -128")
     ;; unfinished
     (byte x)))


(defn unsigned
  [x]
  (assert (<= x 255) "Integer value cannot be greater than 255")
  (assert (>= x 0) "Integer value cannot be less than 0")
  #?(:clj (if (> x 127)
            (byte (bit-or -128 (- x 128)))
            (byte x))
     :cljs (byte x)))


;; (byte -128)
;; (unsigned 255)


(defn shift-right
  ""
  [x n]
  (byte (bit-shift-right (bit-and 0xFF x) n)))


;; (shift-right (unsigned 255) 6)


(defn shift-left
  ""
  [x n]
  (unsigned (bit-and 0xFF (bit-shift-left x n))))


;; (shift-left 0x01 7)

(defn hex-string
  "Returns a hexidecimal representation of a byte value."
  [x]
  (let [s #?(:clj (Integer/toHexString x)
             :cljs (.toString x 16))]
    (case (count s)
      0 "00"
      1 (str "0" s)
      (subs s (- (count s) 2) (count s)))))


;; (hex-string (byte -1))
;; (hex-string (unsigned 255))


(defn binary-string [x]
  (let [s #?(:clj (Integer/toBinaryString x)
             :cljs (.toString x 2))
        c (count s)]
    (if (< c 8)
      (str (apply str (repeat (- 8 c) "0")) s)
      (subs s (- (count s) 8) (count s)))))


;; (binary-string 612)
;; (Integer/toBinaryString 4)
