# CurrencyLoader - Elena Zaharieva

As creating a complete SpringBoot API would be excessive for this straightforward task, I've created the CurrencyLoader class, which performs a single GET request to obtain the most up-to-date currency values based on the current value of USD. If the request is unsuccessful, the currencies are loaded from the cached_rates.txt file, which is located in the project's root directory.

### Run CurrencyLoader
MVN Dependencies -> `exec-maven-plugin` and `org.json`

```shell
mvn exec:java -Dexec.mainClass="dev.converer.cunit.rest.CurrencyLoader"
```

This command will only work if the main method is present within the class. 
It's advisable that you comment it out once you've integrated the class within the other parts of the project.

### How to integrate within the project
```java
CurrencyLoader loader = new CurrencyLoader();
HashMap<String, Double> currencyRates = loader.getCurrencyRates();
```