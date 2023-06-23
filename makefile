ROUND=1
TIME=1
M=50
N=50
X=10

CORE=1

compile:
	javac -cp ".." *.java */*.java

clear:
	rm *.class */*.class

zero:
	java -cp ".." connectx.CXGame $(M) $(N) $(X) connectx.L0.L0 connectx.AFLP.AFLP

one:
	java -cp ".." connectx.CXGame $(M) $(N) $(X) connectx.L1.L1 connectx.AFLP.AFLP

vpz:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.AFLP.AFLP connectx.L0.L0 -v -t $(TIME) -r $(ROUND)

vsz:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.L0.L0  connectx.AFLP.AFLP -v -t $(TIME) -r $(ROUND)

vpu:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.AFLP.AFLP connectx.L1.L1 -v -t $(TIME) -r $(ROUND)
	say finito

vsu:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.L1.L1 connectx.AFLP.AFLP -v -t $(TIME) -r $(ROUND)
	say finito

human:
	  java -cp ".." connectx.CXGame 6 7 4 connectx.AFLP.AFLP

test:
	java -cp ".." connectx.CXPlayerTester 4 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	say finito

full-test:
	java -cp ".." connectx.CXPlayerTester 4 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 4 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 4 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 4 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 4 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 4 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 5 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 6 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1

	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 4 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 5 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 6 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 5 connectx.AFLP.AFLP connectx.L1.L1 -t $(TIME) -r 1
	echo "\n"
	java -cp ".." connectx.CXPlayerTester 7 7 5 connectx.L1.L1 connectx.AFLP.AFLP -t $(TIME) -r 1
