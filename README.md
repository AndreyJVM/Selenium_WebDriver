# **Selenium WedDriver** #
![](/src/main/resources/photo/selenium-java-1.jpg)
Доброго времени суток, меня зовут Андрей Воробьёв, решил собрать и структурировать информацию о Selenium 
WebDriver, которую разобью на несколько статей. Наячём с самого простого и закончим написанием полноценных 
тестов с помощью Selenium WebDriver.

`Используется следующий стек:`

![Linkedin-url](https://img.shields.io/badge/Java-_11-red)

![Linkedin-url](https://img.shields.io/badge/Maven-version_4.0.0-blue)
![Linkedin-url](https://img.shields.io/badge/JUnit_4-version_4.13.2-blue)
![Linkedin-url](https://img.shields.io/badge/Selenium_Java-version_4.10.0-blue)

Основные источники: 
- **Selenium Testing Tools Cookbook** [Unmesh Gundecha](https://github.com/upgundecha)
- Официальная документация [Selenium WebDriver](https://www.selenium.dev/documentation/webdriver/).

Начнём с начала, что такое Selenium WebDriver? Чем он нам поможет в автоматизированном тестировании? 

**Selenium WebDriver** - это инструмент для автоматизации действий веб-браузера.
*В большинстве случаев используется для тестирования Web-приложений*.
В частности, он может быть использован *для решения рутинных задач администрирования
сайта* или регулярного получения данных из различных источников (сайтов).

Так так, теперь по порядку. Зачем нам вообще автоматизировать наши действия? Что мы так, не справимся?
Конечно же справимся, не зря же твоему другу *Чебурашка* (ручному тестировщику) платят денежку, думаю
не зря. К вопросу, как лучше тестировать, мы ещё вернемся, а пока сосредоточимся на автоматизации наших действий.

Программисты и тестировщики не единственные в своем роде ленивые люди. Которые тут пытаются всё
автоматизировать лишь-бы ничего не делать и грести $$$ лопатами. Какие плюсы и минусы от
автоматизации наших тестов и рабочего процесса в целом?

Пример из истории: в 1947 году американская автомобилестроительная компания [Ford](https://ru.wikipedia.org/wiki/Ford),
начала широко использовать термин автоматизация, когда был создан отдел автоматизации. Автоматизация рутинных
процессов вывела компанию на новый уровень, вследствие чего повысила свою прибыль.

Автоматизация позволяет повысить производительность труда, улучшить качество продукции, оптимизировать
процессы управления, отстранить человека от производств, опасных для здоровья.
Автоматизация, за исключением простейших случаев, требует комплексного, системного подхода к решению задачи.

Стоит отметить, что Selenium не будет за нас всё автоматизировать, он даёт нам инструменты 
с помощью которых мы автоматизировать наши тесты. Именно поэтому он широко используется в
автоматизированном тестирование.

# **Первые шаги в Selenium** #
Что бы подключить, *Selenium WebDriver*, нам понадобиться один из сборщиков проектов `Maven / Gradle`
надеюсь они вам знакомы, если нет вот пару ссылок на статьи в Хабре, которые введут вас в курс дела:

- [Apache Maven — основы](https://habr.com/ru/articles/77382/);

- [Краткое знакомство с Gradle](https://javarush.com/groups/posts/2126-kratkoe-znakomstvo-s-gradle).

### Maven ###
Добавляем *dependency* `selenium-java` в фаил `pom.xml`.
Самую свежую версию вы можете найти на сайте [Maven Repository](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)
<details>
    <summary>Maven Selenium Java </summary>

```markdown
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.10.0</version>
</dependency>
```

</details>

### Gradle ###
Добавляем *dependency* `selenium-java` в фаил `build.gradle`
<details>
    <summary>Gradle Selenium Java </summary>

```
testImplementation 'org.seleniumhq.selenium:selenium-java:4.10.0'
```
</details>

### JUnit ###
Добавляем также в *dependency* `JUnit 4` / `JUnit 5`

В конечном итоге получаем вот такой `pom.xml`
<details>
    <summary>pom.xml </summary>

```markdown
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>Selenium_Testing_Tools_Cookbook</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <!--Selenium Java-->
        <selenium-java.version>4.0.0</selenium-java.version>
        <!--JUnit 4-->
        <junit.version>4.13.2</junit.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium-java.version}</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```
</details>


### Разбираем первые Selenium script ###
Все, что делает Selenium, - это отправляет браузеру команды что-то сделать или отправляет запросы
на получение информации. Большая часть того, что вы будете делать с Selenium, -
это комбинация этих основных команд:

1. Запуск браузера, который мы укажем после ключевого слова `new`:
```java
WebDriver driver = new ChromeDriver();
```
2. Открытие страницы, методу `get()` в качестве параметра передаём URL:
```java
  //Вот, можете по залипать немного на миленьких котиков, 
  // но потом обязательно возвращайтесь ко мне =)
  driver.get("https://www.youtube.com/watch?v=UXy0KoQwick");
```
3. Мы также можем получать информацию от браузера например метод `getTitle()` который вернёт
название:
```java
String title = driver.getTitle();
```
4. Часто нам требуется время пока браузер обработает наш запрос, для это в Selenium есть так называем **условия
ожидания**:
```java
driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
```
5. **Поиск элемента на странице**, наши действия мы будем делать после того как найдём нужный нам элемент, без этого 
Selenium нас не поймёт:
В примере мы рассмотрели два способа нахождения элемента через `name` и `cssSelector`
```java
WebElement textBox = driver.findElement(By.name("my-text")); 
WebElement submitButton = driver.findElement(By.cssSelector("button"));
```
6. **Действия над элементом**, хорошо в предыдущем шаге мы нашли элемент, теперь можем производить
всяческие манипуляции:
```java
textBox.sendKeys("Selenium");
submitButton.click();
```
7. **Запросить данные у элемента**: Например текст
```java
String value = message.getText();
```
8. **Закрытие браузера**, После всей нашей проделанном работы нам необходимо завершить сессию:
```java
driver.quit();
```
