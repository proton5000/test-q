# test-q

### Step 1: Run Your Quarkus Application 
  
  Run your Quarkus application using the following command:

  ```./mvnw compile quarkus:dev```

  Your proxy server should now be up and running on `http://localhost:8080`

### Step 2: Access the Proxy

  You can access the proxy by making requests to `http://localhost:8080/proxy/{path}`, 
  where `{path}` is the path you want to access on the target website (e.g., `/quarkus3/`). 
  
  Remember to replace `"https://quarkus.io/"` with the URL of the website you want 
  to proxy in the `TARGET_SITE` variable.

  With this setup, your Quarkus application will act as a proxy server, 
  modifying the text of 6-letter words and handling internal navigation links