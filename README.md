# Классы в Kotlin

## 1 Парадигмы 
***Парадигма*** — набор принципов и моделей мышления при разработке программ

####  1.1 Процедурное программирование
Основной элемент - **функция**, которая выполняет действия и может изменять состояние.
Части кода очень связаны между собой и процедуры (функции) выполняются в чёткой последовательности
```kotlin
var sum = 0
val numbers = listOf(1, 2, 3, 4, 5)
for (n in numbers) {
    sum += n
}
println(sum)
```

#### 1.2	Функциональное программирование
Основная единица — **функция**, которая не изменяет состояние, а возвращает новое значение.
Код представляет собой множество функций, которые принимают данные в одном виде, проводят операции и выдают данные в другом виде.

```kotlin
val numbers = listOf(1, 2, 3, 4, 5)
val sum = numbers.sum()
println(sum)
```

#### 1.3 Объектно-ориентированное программирование
Основная единица - **объект**, созданный на описании класса. 
***Класс*** — это шаблон, описывающий свойства (поля) и поведение (методы).
***Объект*** — конкретный экземпляр класса с конкретным состоянием.
Код представляет собой множество классов, описывающих различные состояния, управление данными и их хранение. Куски кода слабо связаны, большую часть можно переиспользовать

## 2 Синтаксис классов 

Классы в Kotlin состоят из нескольких элементов
-  Свойства. Геттеры и сеттеры
-  Конструкторы
-  Методы 
-  Инициализационные блоки
-  Модификаторы доступа
-  Вложенные и внутренние классы
-  Компаньон-объекты
-  Классы данных


#### 2.1 Свойства
```kotlin
class Address {
    var name: String = "Holmes, Sherlock"
    var street: String = "Baker"
    var city: String = "London"
    var state: String? = null
    var zip: String = "123456"
}
```


***Свойства*** - переменные, хранящие состояние объекта. Объявляются с ключевыми словами val (неизменяемое) или var (изменяемое).

```Kotlin
val address = Address()
println(address.name)
```
Обращение к свойствам идёт через обращение к объекту класса

***Что такое геттеры и сеттеры?***
***Геттер*** — это функция, возвращающая значение свойства.
***Сеттер*** — это функция, задающая значение свойства (только для var).

*field* — это скрытое резервное поле, связанное со свойством

Для нашего класса Address для каждого свойства генерировались геттеры и сеттеры. Под капотом это происходит так: 
```kotlin
class Address {
    var name: String = "Holmes, Sherlock"
        get() = field
        set(value) { field = value }
    ...
}
```

Мы можем изменять дефолтные сеттеры и геттеры

```kotlin
class Person {
    var age: Int = 0
        get() = if (field > 0) field else 0
        set(value) {
            field = if (value >= 0) value else 0
        }
}
```

#### 2.2 Конструкторы
***Конструктор*** — это специальная функция, которая вызывается при создании объекта. Он инициализирует свойства и выполняет любую начальную логику.
В Kotlin есть два вида конструкторов:
1) **Primary construtor**
Объявляется в заголовке класса. Автоматически определяет свойства через val/var

```Kotlin
class Person(val name: String, var age: Int)
```

2. **Secondary Constructor**
Определяются в теле класса с ключевым словом constructor
```kotlin
class Person {
    var name: String
    var age: Int

    constructor(name: String, age: Int) {
        this.name = name
        this.age = age
    }
}
```

В конструкторах можно задавать значение по умолчанию

```kotlin
class Person(val name: String = "Аноним", val age: Int = 18)
```

```kotlin
val person1 = Person() // Аноним, 18
val person2 = Person(name = "Катя") // Катя, 18
val person3 = Person(name = "Дима", agee = 25) // Дима, 25
```

#### 2.3 Методы
***Методы*** - функции, содержащиеся в классе, описывающие его поведение

```Kotlin
class Rectangle(val width: Int, val height: Int) {
    fun area(): Int {
        return width * height
    }
}
```
```Kotlin
val rect = Rectangle(1, 1)
println(rect.area())
```

#### 2.4 Инициализирующие блоки
Это инструмент для выполнения дополнительной логики при создании объекта, который выполняется сразу после вызова конструктора (primary constructor).

```kotlin
class Person(val name: String, val age: Int) {
    init {
        println("Создан человек: $name, возраст: $age")
    }
}
```

Инициализирующих блоков может быть несколько

```kotlin
class Example {
    init {
        println("Первый блок")
    }

    init {
        println("Второй блок")
    }
}
```

#### 2.5 Модификаторы доступа
В Kotlin модификаторы доступа управляют тем, кто может использовать класс, свойство или функцию. Это важный инструмент для инкапсуляции и структурирования кода.

| Модификатор | Видимость                                          | Где можно использовать                        |
| ----------- | -------------------------------------------------- | --------------------------------------------- |
| `public`    | Доступен везде (по умолчанию)                      | В любом месте проекта                         |
| `internal`  | Доступен внутри одного модуля                      | Только в том же модуле (например, библиотека) |
| `protected` | Доступен в классе и его подклассах                 | Только в классах и их наследниках             |
| `private`   | Доступен только в пределах одного файла или класса | Внутри одного класса или файла                |

```kotlin
class User { // public по умолчанию — доступен везде

    // public — доступно отовсюду (по умолчанию)
    val publicName: String = "Иван" // Видно всем

    // private — доступно только внутри этого класса
    private val password: String = "1234" // Скрыто, нельзя использовать даже в наследниках

    // protected — доступно в этом классе и его наследниках
    protected val sessionToken: String = "..." // Видно только внутри и в наследниках

    // internal — доступно в пределах одного модуля (например, одного проекта)
    internal val internalId: Int = 42 // Недоступно за пределами модуля

    fun showInfo() {
        println("Имя: $publicName")
        println("Пароль: $password") // OK: private виден внутри класса
        println("Токен: $sessionToken") // OK: protected виден внутри класса
        println("ID: $internalId") // OK: internal виден в модуле
    }
}

// Наследуемся от User
class Admin : User() {
    fun showAdminAccess() {
        // println(password) // ❌ Ошибка: private недоступно
        println(publicName) // ✅ OK: public доступен
        println(sessionToken) // ✅ OK: protected доступен
        println(internalId) // ✅ OK: internal доступен в модуле
    }
}

fun main() {
    val user = User()

    println(user.publicName)     // ✅ Доступно: public
    // println(user.password)    // ❌ Ошибка компиляции: private недоступен
    // println(user.sessionToken) // ❌ Ошибка компиляции: protected недоступен извне
    println(user.internalId)     // ✅ Доступно: internal (если в том же модуле)
}
```

С методами ситуация та де, что и со свойствами. Модификатор доступа пишется перед ключевым словом fun
```kotlin
private fun privatefunc() {
    println("Это приватная функция")
}
```

#### 2.6 Companion Object
*companion object* — это объект, связанный с классом, а не с его экземплярами. Он используется для хранения **статических данных и методов**.

```kotlin
class MyClass {
    companion object {
        const val VERSION = "1.0"
        fun printVersion() = println("Версия: $VERSION")
    }
}

fun main() {
    println(MyClass.VERSION)        // доступ без создания экземпляра
    MyClass.printVersion()          // тоже
}
```

#### 2.7 Inner и Nested Classes (Вложенные и внутренние классы)
В Kotlin есть два типа вложенных классов

##### Nested class 
Вложенный (по умолчанию static) и не имеет доступа к переменным внешнего класса.

```kotlin
class Outer {
    class Nested {
        fun hello() = println("Привет из вложенного класса")
    }
}

fun main() {
    val nested = Outer.Nested()
    nested.hello()
}
```

##### Inner class
Внутренний (имеет доступ к внешнему классу). Для inner-классов нужно использовать ключевое слово inner.

```kotlin
class Outer(val name: String) {
    inner class Inner {
        fun hello() = println("Привет, $name") // доступ к внешнему name
    }
}

fun main() {
    val outer = Outer("Kotlin")
    val inner = outer.Inner()
    inner.hello()
}
```

#### 2.8 Классы данных
**data class** — специальный класс, который предназначен для **хранения данных**
При объявлении компилятор автоматически генерирует методы:

- equals() — для сравнения объектов по всем свойствам, а не по ссылке
- hashCode() — генерирует уникальный номер на основе свойств объекта
- toString() — удобный вывод объекта в строку
- copy() — для создания копии объекта с возможностью изменить некоторые поля

Чтобы объявить класс данных:

```kotlin
data class User(val name: String, val age: Int)
```

Пример использования классов данных

```kotlin
val user1 = User("Аня", 25)
val user2 = User("Аня", 25)

println(user1)               // User(name=Аня, age=25)
println(user1 == user2)      // true — сравнение по содержимому

val user3 = user1.copy(age = 26)
println(user3)               // User(name=Аня, age=26)

val (name, age) = user3      // деструктуризация
println("$name, $age лет")   // Аня, 26 лет
```


**Материал для самостоятельного изучения**

companion object - https://kotlinlang.org/docs/object-declarations.html#companion-objects

inner class (вложенные и внутренние классы) - https://kotlinlang.org/docs/nested-classes.html#anonymous-inner-classes


## 3. Принципы ООП в Kotlin
Принципы 	Объектно-ориентированного программирования
- Абстракция
- Инкапсуляция
- Наследование
- Полиморфизм

#### 3.1 Абстракция
***Абстракция*** — это выделение только важных характеристик объекта, скрывая детали реализации.
Реализация в Kotlin: абстрактные классы и функции, интерфейсы

##### Интерфейсы 
Контракт без реализации

```kotlin
interface Shape {
    fun area(): Double
    val cornersCount: Int
}

class Circle(val radius: Double) : Shape {
    override fun area(): Double {
        return radius * radius * Math.PI
    }

    override val cornersCount = 0
}

class Square(val side: Double) : Shape {
    override fun area(): Double {
        return side * side
    }
    override val cornersCount = 4
}
```

также в интерфейсах есть реализация по умолчанию, которую можно переопределять

```kotlin
interface Hello {
    fun sayHello() {
        println("Hello World")
    }
}
```

##### абстрактные классы
**abstract class** — класс с частичной реализацией
Похож на интерфейс, позволяет иметь конструктор и частичную реализацию
```kotlin
abstract class Animal(val name: String) {
    abstract fun speak() // абстрактный метод
    fun introduce() = println("Я — $name")
}

class Cat(name: String) : Animal(name) {
    override fun speak() {
        println("Мяу")
    }
}
```

**НЕЛЬЗЯ СОЗДАТЬ ЭКЗЕМПЛЯР ИНТЕРФЕЙСА ИЛИ АБСТРАКТНОГО КЛАССА НАПРЯМУЮ**
```kotlin
val a = Animal("Кто-то") // Ошибка: нельзя создать экземпляр абстрактного класса
```

#### 3.2 Инкапсуляция
Инкапсуляция - **сокрытие** внутреннего состояния объекта и защита от некорректного использования.

В Kotlin реализуется модификаторами доступа, сеттерами, геттерами

```kotlin
class BankAccount {
    private var balance: Int = 0

    fun deposit(amount: Int) {
        if (amount > 0) balance += amount
    }

    fun getBalance(): Int = balance
}
```
!! Переменная balance недоступна напрямую, только через методы deposit() и getBalance().


#### 3.3  Наследование


**Наследование** — это механизм, позволяющий создать *новый класс на основе существующего*, получив его свойства и поведение, с возможностью изменить или дополнить их.


Для того, чтобы сделать класс наследуемым, в Kotlin существует ключевое слово open. От обычных классов нельзя наследоваться. 

```kotlin
class A // нельзя наследовать
open class B // можно
```

Нельзя наследоваться сразу от двух классов, но от нескольких интерфейсов наследоваться можно.

```kotlin
class Parent1
class Parent2

class Child1 : Parent1()            // Всё ок
class Child2 : Parent1(), Parent2() // Ошибка: нельзя унаследоваться от двух классов
```

Пример наследования

```kotlin
open class Animal {
    open fun speak() {
        println("Я животное...")
    }
}
class Cat : Animal() {
    override fun speak() {
        println("Мяу!")
    }
}
```

У базового класса должен быть конструктор, доступный подклассу

```kotlin
open class Person(val name: String)
class Student(name: String, val schoolClass: String) : Person(name)
```

Чтобы обратиться к наследуемому классу нужно написать ключевое слово super

```kotlin
open class A {
    open fun hello() = println("Hello from A")
}
class B : A() {
    override fun hello() {
        super.hello()
        println("Hello from B")
    }
}
```


#### 3.4  Полиморфизм
***Полиморфизм*** (от греч. *"много форм"*) — это способность объектов с одинаковым интерфейсом (например, методом speak() или move()) **вести себя по-разному** в зависимости от их типа.

```kotlin
open class Animal {
    open fun speak() {
        println("Издаёт звук")
    }
}

class Dog : Animal() {
    override fun speak() {
        println("Гав!")
    }
}

class Cat : Animal() {
    override fun speak() {
        println("Мяу!")
    }
}

fun makeAnimalSpeak(animal: Animal) {
    animal.speak()
}
```

##### Для самых любознательных

Полиморфизм - https://habr.com/ru/articles/910194/ (рассказывается про виды и его применение)

Наследование - https://kotlinlang.org/docs/inheritance.html

sealed classes - https://kotlinlang.org/docs/sealed-classes.html


## Вопросы для повторения
- Чем отличается val от var в свойствах класса?
- Что такое геттер и сеттер в Kotlin?
- В чём разница между primary и secondary constructor?
- Зачем нужны init блоки?
- Какие бывают модификаторы доступа и где они применяются?
- Чем отличаются интерфейс и абстрактный класс?
- Что такое companion object и зачем он нужен?
- Что делает ключевое слово inner?
- Объясни принципы ООП: абстракция, инкапсуляция, наследование, полиморфизм.
- Что делает override и когда он используется?
- Почему от обычного класса нельзя унаследоваться без open?

## Домашнее задание
1. Создай класс Book с полями title, author, year. Добавь метод info() для вывода информации о книге. Поставь кастомный геттер для года, сделай так, чтобы при его получении он всегда был больше или равен 1900 (обработай случай, когда year меньше 1900)
2. Создай абстрактный класс Employee с полем name и абстрактным методом work(). Реализуй классы Programmer и Designer.
3. Создай класс House и внутренний класс Room, в котором метод describe() может обращаться к полям House.

## Задание повышенной сложности
Создай data class Programmer с полями: name: String, age: Int, experienceYears: Int, languages: List<String>.
Добавь следующие функции и возможности:
1) Функция primaryLanguage() — возвращает язык программирования с наибольшей длиной названия из списка (например, "JavaScript" длиннее, чем "C++"). Если список пуст, возвращай "No languages".
2) Метод isSenior() — возвращает true, если стаж работы 5 лет и больше, иначе false.
3) Переопредели метод toString(), чтобы выводить информацию о программисте в удобочитаемом виде
4) Используй метод copy(), чтобы создать нового программиста с теми же языками, но другим именем и опытом работы.
5) Напиши функцию, которая принимает список программистов и возвращает средний возраст.
