#lang racket

(require drracket/check-syntax)

(define (x file-name)
  (show-content file-name))
(define details (x "test.rkt"))

(for-each
 (λ (d)
   (match d
     ;;; new definition location
     [(vector syncheck:add-definition-target start end name l)
      (printf "~a~n" name)]
     ;;; unused import
     [(vector syncheck:add-unused-require start end)
      (printf "unused require~n")]
     ;;; popup message when mouse on it
     [(vector syncheck:add-mouse-over-status start end message)
      (void)]
     [_ (printf "~a~n" d)]))
 details)
