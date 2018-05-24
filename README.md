# Mail test application

This application is used for test if java can send email in local server.

## Usage

Three options:

### Set configuration in `application.yml`

Edit the `application.yml` file before build the application. Set the correct info in `spring.mail` section (see [https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-email.html](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-email.html))

After build use with the correct profile and an email:

```bash
java -Dspring.profiles.active=production -jar target/mail-test-0.0.1-SNAPSHOT.jar "email@email.com"
```

or with extra params:

```bash
java -Dspring.profiles.active=production -jar target/mail-test-0.0.1-SNAPSHOT.jar "email@email.com" "subject" "message"
```

### Set configuration in `application.yml` with encrypted info

If you need crypt info (like the password), you can crypt any configuration key in `application.yml` with [jaspyt framework](http://www.jasypt.org).

Firts, you need crypt the _key_value_:

```bash
java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="KEY_VALUE" password="custom-password" algorithm=PBEWithMD5AndDES
```

> Use a custom _password_ and don't storage in 'application.yml'

Sample output:

```console
# java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="a key value" password="custom-password" algorithm=PBEWithMD5AndDES

----ENVIRONMENT-----------------

Runtime: Oracle Corporation Java HotSpot(TM) 64-Bit Server VM 25.162-b12



----ARGUMENTS-------------------

algorithm: PBEWithMD5AndDES
input: a key value
password: custom-password



----OUTPUT----------------------

VnS9Hrbh5KlmqZXeW9E2A1Y9JCh3F0YM

```

Use the output with `ENC` command, like:

```yaml
spring:
    mail:
        password: ENC(VnS9Hrbh5KlmqZXeW9E2A1Y9JCh3F0YM)
```

After build the app, you cant usage with `jasypt.encryptor.password` enviroment property:

```bash
java -Dspring.profiles.active=development -Djasypt.encryptor.password="custom-password"  -jar target/mail-test-0.0.1-SNAPSHOT.jar "email@email.com"
```
