build:
	javac -cp .:junit-4.10.jar */**.java

run: build
	java game.GameViewController

test: build
	java -cp .:junit-4.10.jar org.junit.runner.JUnitCore model.AllTests

clean:
	rm */**.class
