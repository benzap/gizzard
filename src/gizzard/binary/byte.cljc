(ns gizzard.binary.byte
  "My own take on byte conversion. `(byte)` doesn't work with
  unsigned-bytes.")


(def signed-byte byte)


(defn unsigned-byte
  ;; FIXME: Implement
  [x]
  (assert (<= x 255) "Integer value cannot be greater than 255")
  (assert (>= x 0) "Integer value cannot be less than 0")
  )
