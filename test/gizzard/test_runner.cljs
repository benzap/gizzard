(ns gizzard.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests doo-all-tests]]

   ;; Tests
   
   ;; ./
   [gizzard.core-test]

   ;; ./binary
   [gizzard.binary.byte-test]))


(doo-tests
 
 ;; ./
 'gizzard.core-test

 ;; ./binary
 'gizzard.binary.byte-test)
