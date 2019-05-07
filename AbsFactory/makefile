#
# makefile
#
# @author Emily
# @version Spring 2019
#

HAMCREST_JAR = /usr/share/java/hamcrest/core-1.1.jar
JSON_SIMPLE_JAR = json_simple.jar
APP_FILES = Scatt.java Submission.java ProjectAnalyzer.java FileUtils.java SpriteParser.java StageParser.java CategoryMap.java Block.java
VER_2_FILES = Ver2CatMap.java Ver2ProjA.java Ver2SpritePars.java Ver2StagePars.java
VER_3_FILES = Ver3CatMap.java Ver3ProjA.java Ver3SpritePars.java Ver3StagePars.java
REPORT_FILES = Report.java TextReport.java JsonReport.java
APP_CLASS_FILES = Scatt.class Submission.class ProjectAnalyzer.class FileUtils.class SpriteParser.class StageParser.class CategoryMap.class Block.class
VER_2_CLASS_FILES = Ver2CatMap.class Ver2ProjA.class Ver2SpritePars.class Ver2StagePars.class
VER_3_CLASS_FILES = Ver3CatMap.class Ver3ProjA.class Ver3SpritePars.class Ver3StagePars.class
REPORT_CLASS_FILES = Report.class TextReport.class JsonReport.class

default:
	@echo "usage: make target"
	@echo "available targets: compile, jar, run, scatt, clean"

compile: $(APP_FILES) $(VER_2_FILES) $(VER_3_FILES) $(REPORT_FILES)
	javac -cp .:$(JSON_SIMPLE_JAR) $(APP_FILES) $(VER_2_FILES) $(VER_3_FILES) $(REPORT_FILES)

jar: $(APP_CLASS_FILES) $(VER_2_CLASS_FILES) $(VER_3_CLASS_FILES) $(REPORT_CLASS_FILES)
	jar -cvmf MANIFEST.MF Scatt.jar $(JSON_SIMPLE_JAR) $(APP_CLASS_FILES) $(VER_2_CLASS_FILES) $(VER_3_CLASS_FILES) $(REPORT_CLASS_FILES)

run: Scatt.jar
	java -jar Scatt.jar

scatt: $(APP_FILES) $(VER_2_FILES) $(VER_3_FILES) $(REPORT_FILES)
	javac -cp .:$(JSON_SIMPLE_JAR) $(APP_FILES) $(VER_2_FILES) $(VER_3_FILES) $(REPORT_FILES)
	jar -cvmf MANIFEST.MF Scatt.jar $(APP_CLASS_FILES) $(VER_2_CLASS_FILES) $(VER_3_CLASS_FILES) $(REPORT_CLASS_FILES)
	java -jar Scatt.jar

clean:
	rm -f *.class
	rm -f Scatt.jar
	rm -rf expected
	rm -rf zips
	rm -rf unzips
	rm -f Report*.txt
