compile:
	@mvn compile -f app/pom.xml

package:
	@mvn package -f app/pom.xml

build:
	@make compile
	@make package

watch:
	@while true; do \
		inotifywait -qr -e modify -e create -e delete -e move app/src/main; \
		make build; \
	done