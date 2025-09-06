# 📱 Phishing SMS Detection System

A Spring Boot application designed to detect phishing URLs embedded in SMS messages by integrating with the [VirusTotal API](https://virustotal.com). The system extracts URLs from SMS content, encodes them into Base64 format as required by VirusTotal, and classifies them as **Safe**, **Suspicious**, or **Malicious**. Results are presented in a user-friendly web interface.

<img width="1123" height="870" alt="Screenshot 2025-09-06 140219" src="https://github.com/user-attachments/assets/20cc40b4-614b-43e8-baf3-c01ce69dcc0f" />

## 🚀 Features
- 🔍 **SMS Content Analysis**: Extracts URLs from SMS messages using regex patterns.
- 🌐 **VirusTotal Integration**: Queries the VirusTotal API with Base64-encoded URLs to analyze their safety.
- 📊 **Threat Classification**: Categorizes URLs as *Safe*, *Suspicious*, or *Malicious* based on VirusTotal's response.
- 🎨 **Frontend UI**: Provides a simple HTML/CSS/JavaScript form for inputting SMS content and displaying results with categorized bullet points.
- ⚙️ **REST API**: Exposes a Spring Boot REST endpoint to process SMS checks.
- 🛡️ **Secure Configuration**: Manages VirusTotal API key via `application.properties` for secure and flexible setup.

## 🏗️ Tech Stack
- **Backend**: Java 17, Spring Boot, Gradle
- **Frontend**: HTML, CSS, JavaScript
- **API Integration**: VirusTotal API
- **Libraries**: Jackson (JSON parsing), Spring Web, Java Regex
- **Protocol**: REST API

## 📂 Project Structure
```
phishing-sms-detector/
├── src/main/java/com/example/phishingdetector/
│   ├── controller/PhishingController.java       # REST controller for handling API requests
│   ├── service/VirusTotalService.java           # Service layer for VirusTotal API integration
│   └── PhishingDetectorApplication.java         # Main Spring Boot application class
├── src/main/resources/
│   ├── static/index.html                        # Frontend UI (HTML, CSS, JavaScript)
│   └── application.properties                   # Configuration file for API key and server settings
├── build.gradle                                 # Gradle build configuration
└── README.md                                    # Project documentation
```

## ⚡ Getting Started

### 1️⃣ Prerequisites
- **Java 17+**: Ensure Java Development Kit (JDK) 17 or higher is installed.
- **Gradle**: Use the included Gradle wrapper (`./gradlew`) or install Gradle manually.
- **VirusTotal API Key**: Obtain a free API key from [VirusTotal](https://www.virustotal.com/gui/my-apikey).

### 2️⃣ Configuration
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd phishing-sms-detector
   ```
2. Add your VirusTotal API key to `src/main/resources/application.properties`:
   ```properties
   virustotal.api.key=YOUR_API_KEY_HERE
   server.port=8080
   ```

### 3️⃣ Build and Run
1. Build the project:
   ```bash
   ./gradlew clean build
   ```
2. Run the Spring Boot application:
   ```bash
   ./gradlew bootRun
   ```

### 4️⃣ Access the Application
- Open a browser and navigate to: [http://localhost:8080/index.html](http://localhost:8080/index.html)
- Enter an SMS message containing a URL and click "Check for Phishing" to view the results.

## 📡 API Endpoints
- Detect SMS Phishing
   ```bash
   POST http://localhost:8080/api/phishing/detect-sms
   ```
<img width="1916" height="1031" alt="Screenshot 2025-09-06 144104" src="https://github.com/user-attachments/assets/562e631f-bd92-4212-a257-4deb73c70f5d" />

   
## 🛠️ Development Notes
- ✅ **Modern Java Compliance**: Uses `URI` instead of the deprecated `URL(String)` constructor for Java 20+ compatibility.
- ✅ **Robust Error Handling**: Implements proper exception handling for API and network errors.
- ✅ **Secure Configuration**: Avoids hardcoding sensitive data by using `application.properties` for API key management.
- ✅ **Clean Architecture**: Maintains separation of concerns with distinct Controller and Service layers.

## 🔮 Future Improvements
- 🌟 **Multi-URL Support**: Enhance the system to detect and analyze multiple URLs in a single SMS.
- 💾 **Database Integration**: Persist analysis results in a database (e.g., MySQL or PostgreSQL) for historical tracking.
- 🎨 **Frontend Enhancements**: Upgrade the frontend with modern frameworks like React or Vue for improved UI/UX.
- 🔐 **API Authentication**: Add user authentication to secure API access and usage.

## 🧪 Testing
- Run unit tests with:
  ```bash
  ./gradlew test
  ```
- Ensure the VirusTotal API key is valid and the network is accessible during testing.

## 👨‍💻 Author
Developed by [ABHIJEET DUBEY](https://github.com/abhijeet-code/)

## 📞 Contact
For questions or contributions, please open an issue or submit a pull request on the project's repository.
