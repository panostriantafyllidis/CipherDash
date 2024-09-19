# CipherDash

Task: (the original as seen below , was changed once I got bored and started playing with it)

• Encode the 1st character of the message with its corresponding integer value (from ASCII code)

• Encode the following characters: (a) by adding the ASCII integer value of each of them to the code of its predecessor, (b) taking the remainder of dividing this sum by a constant

• The constant is called the encryption key and is (supposedly) secret

• We assume that messages end with the character #

• Write a java program that implements the encryption algorithm so that the resulting encoded message is a sequence of integers ending with -1

• Write also the decryption algorithm that takes as input a sequence of integers ending with -1 and computes the original message

# Running the Java Project in VS Code

## Prerequisites

1. **Java Development Kit (JDK)** installed on your system. Make sure you have set the `JAVA_HOME` environment variable.
2. **Java Extension Pack** installed in VS Code.

## Project Structure

```
CipherDash
│   .gitignore
│   CipherDash.iml
│   README.md
│
├───out
│   └───production
│       └───CipherDash
│           └───com
│               └───Byron
│                   └───cipherdash
│                       └───CryptoDriver.class
│                       └───CryptoPOJO.class
│
└───src
    └───com
        └───Byron
            └───cipherdash
                └───CryptoDriver.java
                └───CryptoPOJO.java
```

## Step-by-Step Guide

### 1. **Open the Project in VS Code**

- Open **VS Code**.
- Select `File > Open Folder...` and choose the **CipherDash** project folder.

### 2. **Set Up VS Code for Java**

- If you haven't installed the **Java Extension Pack**, do so:
  1. Press `Ctrl+Shift+X` to open the Extensions view.
  2. Search for **"Java Extension Pack"** and click **Install**.

### 3. **Configure `launch.json` (Optional)**

You can configure VS Code to run your Java program more conveniently using a `launch.json` file.

1. Open the **Command Palette** (`Ctrl+Shift+P`) and type `Java: Configure Java Runtime`.
2. Select your JDK if it's not already selected.

To create a `launch.json` manually:

1. Click the **Run and Debug** tab (or press `Ctrl+Shift+D`).
2. Click **Create a launch.json file**.
3. Add the following configuration to launch `CryptoDriver.java`:

```
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch CryptoDriver",
            "request": "launch",
            "mainClass": "com.Byron.cipherdash.CryptoDriver",
            "projectName": "CipherDash"
        }
    ]
}
```

### 4. **Compile and Run in VS Code**

#### **Option 1: Using Run Button**

- Open `CryptoDriver.java` in the editor.
- At the top right corner, you'll see a **Run** button (green triangle icon). Click it to run the file.

#### **Option 2: Using Terminal**

1. Press `Ctrl+` to open the terminal.
2. Compile the project:

```
javac -d out/production/CipherDash src/com/Byron/cipherdash/*.java
```

3. Run the `CryptoDriver` class:

```
   java -cp out/production/CipherDash com.Byron.cipherdash.CryptoDriver
```

### 5. **Debugging**

1. Set breakpoints in your Java code by clicking next to the line numbers in `CryptoDriver.java`.
2. Press `F5` or click **Run > Start Debugging** to start debugging the program.
3. The debugger will stop at the breakpoints, allowing you to inspect variables and step through the code.

## Additional Tips

- **Build Tools**: If you are using build tools like **Maven** or **Gradle**, make sure they are correctly configured. You can use `mvn compile` or `./gradlew build` to build your project.
- **Classpath Issues**: Ensure your compiled `.class` files are in the correct directory structure (`out/production/CipherDash/com/Byron/cipherdash`).

```

```
