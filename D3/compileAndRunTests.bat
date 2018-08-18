del result.xml
del report.txt
del testclasses\edu\gatech\seclass\capitalize\MyMainTest.class
javac -cp lib\capitalize.jar;lib\tests.jar;lib\commons-lang3-3.5.jar;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar -d testclasses testsrc\edu\gatech\seclass\capitalize\*
java -cp lib\tests.jar;testclasses;lib\capitalize.jar;lib\commons-lang3-3.5.jar;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar;lib\jcov_file_saver.jar org.junit.runner.JUnitCore edu.gatech.seclass.capitalize.MyMainTest