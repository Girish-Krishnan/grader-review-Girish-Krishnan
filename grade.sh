CPATH='.:lib/hamcrest-core-1.3.jar:lib/junit-4.13.2.jar'

rm -rf student-submission
rm -rf grading-area

mkdir grading-area
cp -r lib ./grading-area
cp TestListExamples.java ./grading-area

git clone $1 student-submission
echo 'Finished cloning'


# Draw a picture/take notes on the directory structure that's set up after
# getting to this point

# Then, add here code to compile and run, and do any post-processing of the
# tests

# Check if student-submission/ListExamples.java exists
if [ ! -f student-submission/ListExamples.java ]; then
    echo "[ERROR] ListExamples.java not found in the root of the repository. Please make sure that the file is in the root of the repository and try again. Also, check that the file is named correctly."
    echo "Grade: 0 / 10"
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
    echo "Grade: 0 / 10"
    exit 1
fi

# Else, run the tests on ListExamples.class
cd grading-area

if [ ! -f ListExamples.class ]; then
    echo "[ERROR] ListExamples.class not found. The code did not compile successfully, so please see the error message above"
    echo "Grade: 0 / 10"
    exit 1
fi

javac -cp $CPATH TestListExamples.java
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples 
java -cp $CPATH org.junit.runner.JUnitCore TestListExamples > test-results.txt

# Extract the last line to see how many tests fail
# If the number of tests that fail is greater than 0, print an error message
# If OK is found in the last line, print a success message
ok=$(tail -n 1 test-results.txt | grep -c "OK")
if [ $ok -eq 0 ]; then
    # Failures: X
    failures=$(tail -n 2 test-results.txt | head -n 1 | grep -o '[0-9]*$')
    #tests=$(tail -n 2 test-results.txt | head -n 1 | grep -o '^[0-9]*')
    tests=$(tail -n 2 test-results.txt | grep -oE '[0-9]+' | sed -n 1p)
    echo "Grade: $((tests - failures))/$((tests))"
else
    echo "All tests passed. Score: 10/10"
fi
