# Index

- [Project](#-project-)

- [H2 Database](#-h2-database-)
  - [Dependency](#1-dependency--h2-database)
  - [Properties](#2-properties)
    - [Spring Security](#imp--if-you-are-using-spring-security-)
  - [How to Connect to H2 Console](#3-how-to-connect-to-a-h2-console)
  - [Learned From](#learned-from)
- [Schema.sql](#-schemasql-)
- [CommandLineRunner](#-commandlinerunner-)
- [Other Notes](#-other-notes-)

---

# • Project •

```properties
spring.application.name=H2Demo
server.port=9090
.... others
```

###### Structure

| Packages          | Files contains                 |
| :---------------- | :----------------------------- | ------------------------------------- |
| `config`          | SecurityConfig.java            |
| `Modek or Entity` | Book.java                      |
| `controller`      | BookController.java            |
| `service`         | DataInitialization.java        |
| `repsitory`       | BookRepository.java `Interface | exteds CrudRepository<Book, Integer>` |

###### API's

`localhost/9090/api/books` : Get

<hr/>

# • H2 Database •

<hr/>

## 1. Dependency : `"H2 Database"`

<hr/>

## 2. Properties

###### console properties [ spring -> h2 -> console ]

```properties
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

###### `<Opt>` Database properties [ spring -> datasource ]

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

```

<hr/>

###### IMP : If you are using Spring Security 🔐

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions().disable())
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/h2-console/**").permitAll()
            .anyRequest().authenticated()
        );
    return http.build();
}

```

- `headers.frameOptions().disable()` : otherwise will show `❌ "Refused to display 'localhost' in a frame because it set 'X-Frame-Options' to 'deny'"`

<hr/>

## 3. How to connect to a h2-console

1] Copy `url` after running application from CMD

```
H2 console available at '/h2'. Database available at 'jdbc:h2:mem:8877b728-91d8-4f81-bae1-e10a6af28ace'
```

or
If you have set `spring.datasource.url=jdbc:h2:mem:<yourdbname>` :: copy this url

2] Go to `http://localhost:9090/h2`
3] Paste `url` at `JDBC URL`:

![H2 Console](./readme-images/image.png)

4] `Test connection` and `connect`.

<hr/>

### Learned from

https://www.youtube.com/watch?v=PSrHcCwvfVQ

<hr/> <br/> <hr/>

# • schema.sql •

- To use schema.sql `for MySQL` you need this `2 properties`

  ```properties
  spring.jpa.hibernate.ddl-auto=none **
  spring.sql.init.mode=always **
  spring.sql.init.platform=mysql
  ```

  - `ddl-auto=none` → So Hibernate does NOT create tables automatically
  - `spring.sql.init.mode=always` → Makes Spring run schema.sql
  - `platform=mysql` → Tells Spring to use MySQL-style SQL (optional but good)

<hr/>

# • CommandLineRunner •

```java
@Component
@Order(1) //If you have multiple runner
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("App just started!");
    }
}
```

or

```java
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {...}

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository repo) {
        return args -> {
            System.out.println("Application Started…");
            repo.save(new User("admin", "password"));
        };
    }
}
```

- `When the application starts → this message prints.`

<hr/>

# • Other Notes •

1] CommandLine Runner for JPA vs Schema.sql `Preferred`

2] To use schema.sql for MySQL you need that mentioned ☝🏻 2 properties

3] Data JDBC vs Data JPA
`If you want simple + fast + predictable SQL, choose Spring Data JDBC; `
`If you want rich ORM features + relationships + lazy loading, choose Spring Data JPA.`

4] What is record -> `it has getters, setters... etc by default.`

<hr/>
