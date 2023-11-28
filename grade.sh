CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area

git clone $1 student-submission
echo 'Finished cloning'


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

# Check if student-submission/ListExamples.java exists
if [ ! -f student-submission/ListExamples.java ]; then
    echo "[ERROR] ListExamples.java not found in the root of the repository. Please make sure that the file is in the root of the repository and try again. Also, check that the file is named correctly."
    exit 1
fi

# Else, move it to the grading area and compile it
mv student-submission/ListExamples.java grading-area

# Try compiling ListExamples.java and store the result in a variable
# If the compilation fails, print an error message indicating what the compilation error was
# and exit with a non-zero exit code
javac -cp $CPATH grading-area/ListExamples.java
if [ $? -ne 0 ]; then
    echo "[ERROR] ListExamples.java failed to compile. Please make sure that the file compiles and try again. The error message is shown above."
    exit 1
fi

# Else, run the tests on ListExamples.class
javac -cp $CPATH TestListExamples.java
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples