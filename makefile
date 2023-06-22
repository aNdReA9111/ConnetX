ROUND=10
TIME=2
M=6
N=7
X=4



compile:
	javac -cp ".." *.java */*.java

clear:
	rm *.class */*.class

zero:
	java -cp ".." connectx.CXGame $(M) $(N) $(X) connectx.L0.L0 connectx.LLAPlayer.LLAPlayer

one:
	java -cp ".." connectx.CXGame $(M) $(N) $(X) connectx.L1.L1 connectx.LLAPlayer.LLAPlayer

vpz:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.LLAPlayer.LLAPlayer connectx.L0.L0 -v -t $(TIME) -r $(ROUND)

vsz:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.L0.L0  connectx.LLAPlayer.LLAPlayer -v -t $(TIME) -r $(ROUND)

vpu:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.LLAPlayer.LLAPlayer connectx.L1.L1 -v -t $(TIME) -r $(ROUND)

vsu:
	java -cp ".." connectx.CXPlayerTester $(M) $(N) $(X) connectx.L1.L1  connectx.LLAPlayer.LLAPlayer -v -t $(TIME) -r $(ROUND)
