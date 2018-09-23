# Makefile for Gizzard
.PHONY: test test-clj test-cljs
.PHONY: help clean version


help:
	@echo "Gizzard Makefile"
	@echo ""
	@echo "  Commands:"
	@echo ""
	@echo "    test       Perform all tests."
	@echo "    test-clj   Perform tests in clojure."
	@echo "    test-cljs  Perform tests in clojurescript."
	@echo ""
	@echo "    clean      Remove all build artifacts."
	@echo "    version    Get Project Version."


test: test-clj test-cljs


test-clj:
	lein test


test-cljs:
	lein doo once


clean:
	lein clean
	rm -rf ./target
	rm -rf ./resources/public/js/compiled

version:
	lein project-version


