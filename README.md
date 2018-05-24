# MAil test application

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

### With jasypt password

If you need crypt your password, you can crypt any configuration key in `application.yml`.

```bash
java -cp jasypt-1.9.2.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="KEY_VALUE" password="custom-password" algorithm=PBEWithMD5AndDES
```

> Use a custom _password_ and don't storage in 'application.yml'



```bash
java -Dspring.profiles.active=development -Djasypt.encryptor.password="custom-password"  -jar target/mail-test-0.0.1-SNAPSHOT.jar "erik.valdivieso@infotec.mx"
```

## Config

Edit



