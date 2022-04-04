Commands to launch tests: 
1. mvn test -DscopeXml="testng.xml"
2. mvn test -DscopeXml="cucumber.xml"

Allure and cucumber reports are generated in: {project root}/test-reports. To see Allure reports, launch the following command in the {project root}/test-reports directory: 
allure serve
