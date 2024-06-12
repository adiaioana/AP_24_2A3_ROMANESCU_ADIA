REM Compile and run server application
cd ServerApplication
javac -cp . src\main\java\org\example\Main.java
start java -cp . src.main.java.org.example.Main
cd ..

REM Wait for server to start (adjust timeout as needed)
timeout /t 5

REM Compile and run client application (two instances)
cd ClientApplication
javac -cp . src\main\java\org\example\Main.java
start java -cp . src.main.java.org.example.Main
start java -cp . src.main.java.org.example.Main
cd ..
