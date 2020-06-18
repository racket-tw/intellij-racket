#lang racket

(provide digano-file
         diganostic->json)

(require json)
(require drracket/check-syntax)

(define (digano-file file-name)
  (show-content file-name))

(define (diganostic->json diganostic)
  (match diganostic
    ;;; new definition location
    [(vector syncheck:add-definition-target start end name l)
     (make-hash (list (cons 'type "NewDefinition")
                      (cons 'start start)
                      (cons 'end end)
                      (cons 'name name)))]
    ;;; unused import
    [(vector syncheck:add-unused-require start end)
     (make-hash (list (cons 'type "UnusedRequire")
                      (cons 'start start)
                      (cons 'end end)))]
    ;;; popup message when mouse on it
    [(vector syncheck:add-mouse-over-status start end message)
     (make-hash (list (cons 'type "OnHoverInfo")))]
    [_ (make-hash (list (cons 'type "Ignore")))]))
