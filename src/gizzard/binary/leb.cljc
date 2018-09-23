(ns gizzard.binary.leb
  "LEB128 Signed and Unsigned Encoder / Decoder

   https://en.wikipedia.org/wiki/LEB128"
  #?(:clj (:import java.nio.ByteBuffer)))


(defprotocol LEB128
  (encode [this]))


(defn pad-bytes [xs]
  (let [c (count xs)]))


(defn encode-unsigned
  "Encodes an sequence of bytes into a LEB128 sequence"
  [xs]
  )
