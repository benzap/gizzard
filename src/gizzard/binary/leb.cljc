(ns gizzard.binary.leb
  "LEB128 Signed and Unsigned Encoder / Decoder

   https://en.wikipedia.org/wiki/LEB128"
  (:require
   [gizzard.binary.byte :as byte])
  #?(:clj (:import java.nio.ByteBuffer)))


(defprotocol LEB128
  (encode-unsigned [this])
  (encode-signed [this]))


(defn encode-unsigned*
  "Encodes a binary value into an unsigned LEB128 sequence."
  [x]
  (assert (>= x 0) "Value must be greater than or equal to 0")
  (loop [value x out []]
    (let [b8 (byte/unsigned (bit-and 2r01111111 value))
          value (bit-shift-right value 7)]
      (if (not= value 0)
        (recur value (conj out (byte/unsigned (bit-flip b8 7))))
        (conj out b8)))))


(defn decode-unsigned
  "Decodes an unsigned LEB128 sequence into a Long."
  [xs]
  ;;TODO: implement in the future
  )


(defn encode-signed*
  "Encodes a binary value (Long) into a signed LEB128 sequence."
  [x]
  (let [negative? (neg? x)]
    (loop [value x out []]
      (let [b8 (byte/unsigned (bit-and 2r01111111 value))
            value (bit-shift-right value 7)]
        (if (or (and (= value 0) (not negative?))
                (and (= value -1) negative?))
          (conj out b8)
          (recur value (conj out (byte/unsigned (bit-flip b8 7)))))))))
  

;;(println (Integer/toBinaryString 624485))
;;(map byte/binary-string (encode-signed* -424091))


(defn decode-signed
  "Decodes a signed LEB128 sequence into a Long."
  [xs]
  ;;TODO: implement in the future
  )


(extend-protocol LEB128
  #?(:clj java.lang.Long :cljs js/Number)
  (encode-unsigned [this]
    (encode-unsigned* this))

  (encode-signed [this]
    (encode-signed* this)))
