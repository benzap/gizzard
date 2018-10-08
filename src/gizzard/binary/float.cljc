(ns gizzard.binary.float
  (:require
   [gizzard.binary.byte :as byte])
  #?(:clj (:import java.nio.ByteBuffer)))


#?(:clj
   (defn convert-to-bytes [x]
     (-> (ByteBuffer/allocate 4)
         (.putFloat x)
         (.array)
         vec)))


#?(:cljs
   (defn js-convert-to-bytes
     [x]
     (let [buffer (new js/ArrayBuffer 4)
           float-view (new js/Float32Array buffer)
           int-view (new js/Int8Array buffer)]
       (aset float-view 0 x)
       (.log js/console int-view)
       [(byte/signed (aget int-view 3))
        (byte/signed (aget int-view 2))
        (byte/signed (aget int-view 1))
        (byte/signed (aget int-view 0))])))


(defn to-byte-vector
  "Converts a float value into a vector of bytes"
  [x]
  #?(:clj (convert-to-bytes x)
     :cljs (js-convert-to-bytes x)))
  

;;(vec (to-byte-array -3.14))
  
