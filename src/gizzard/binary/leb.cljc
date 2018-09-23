(ns gizzard.binary.leb
  "LEB128 Signed and Unsigned Encoder / Decoder

   https://en.wikipedia.org/wiki/LEB128"
  #?(:clj (:import java.nio.ByteBuffer)))


(defprotocol LEB128
  (encode [this]))


(defn encode-unsigned
  "Encodes an sequence of bytes into a LEB128 sequence"
  [xs])


(def x (byte-array [(byte 0x43) 
                           (byte 0x6c)
                           (byte 0x6f)
                           (byte 0x6a)
                           (byte 0x75)
                           (byte 0x72)
                           (byte 0x65)
                           (byte 0x21)]))

(println (to-byte-array 12314512))
