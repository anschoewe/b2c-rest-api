# Build locally with gradle

`./gradlew build && java -jar build/libs/b2c-rest-api-0.0.1-SNAPSHOT.jar`

# Generate public/private keys at the same time.  The password encrypting the private key is 'server'
```
openssl req \
    -new \
    -newkey rsa:2048 \
    -days 1024 \
    -passout pass:server \
    -x509 \
    -subj "/CN=127.0.0.1" \
    -keyout serverprivate.key \
    -out serverCA.crt
```

# Generate Java keystore that will store the public/private keys (cert) to secure a TLS connection
`openssl pkcs12 -export -in serverCA.crt -inkey serverprivate.key -certfile serverCA.crt -out keystore.p12 -passin pass:server -passout pass:changeit`

# General a client certificate
```
openssl req \
    -new \
    -newkey rsa:2048 \
    -days 1024 \
    -passout pass:client \
    -x509 \
    -subj "/CN=myclient" \
    -keyout clientprivate.key \
    -out clientCA.crt
```

# Generate Java truststore that will store the public/private keys (cert) to validate the cert presented by client calling
`keytool -import -file clientCA.crt -alias clientCA -keystore truststore.p12 -storetype pkcs12`

# Example of how to test mutual tls locally using curl.  
You'll notice the password 'client' after the cert argument (:) 
`curl --insecure --cert clientCA.crt:client --key clientprivate.key -H "Content-Type:application/json;Charset='UTF-8'" https://127.0.0.1:8901/`
