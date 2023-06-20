compile:
	javac -cp ".." *.java */*.java

clear:
	rm *.class */*.class

zero:
	java -cp ".." connectx.CXGame 6 7 4 connectx.L0.L0 connectx.LLAPlayer.LLAPlayer 

one:
	java -cp ".." connectx.CXGame 6 7 4 connectx.L1.L1 connectx.LLAPlayer.LLAPlayer

vpz:
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.LLAPlayer.LLAPlayer connectx.L0.L0 -v -t 2 -r 10

vsz:
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.L0.L0  connectx.LLAPlayer.LLAPlayer -v -t 2 -r 10

vpu:
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.LLAPlayer.LLAPlayer connectx.L1.L1 -v -t 2 -r 10

vsu:
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.L1.L1  connectx.LLAPlayer.LLAPlayer -v -t 2 -r 10