## Домашнее задание №5
1. Ответить на вопросы:
№Почему на любом объекте можем вызвать метод getClass()?
Ответ: Метод getClass() является частью класса Object, который является базовым классом для всех других классов в Java. Поэтому любой объект в Java имеет доступ к этому методу.

№Почему на любом классе можем вызвать .class?
.class — синтаксис, который предоставляет объект типа Class, содержащий метаданные о типе (классах, интерфейсах, массивах, примитивах и т.д.). 
Это доступно для каждого типа, поскольку компилятор автоматически создает объект Class для всех типов и загружает их в JVM.
Почему это возможно?
1. Class встроен в архитектуру Java: каждое описание типа представлено объектом Class.
2. Используется для Reflection: позволяет анализировать классы, их методы и поля.
3. 

№В чём отличие динамического прокси от статического?
Отличия между динамическим и статическим прокси в Java:

1. Статический прокси:
   - Классы-прокси создаются вручную (собственной реализацией интерфейса).
   - Реализация прокси-класса фиксирована во время написания кода.

2. Динамический прокси:
   - Классы-прокси создаются динамически, во время выполнения, с использованием механизма Reflection (Proxy.newProxyInstance).
   - Для этого прокси должен реализовывать хотя бы один интерфейс.
   
№Приведите преимущества и недостатки.

Преимущества и недостатки динамического прокси:

Преимущества:
   1. Не нужно создавать вручную новые классы для прокси, все происходит динамически.
   2. Один прокси может быть использован для множества интерфейсов.
   3. Гибкость: можно добавить поведение или изменить логику во время выполнения.

Недостатки:
   1. Работает только с интерфейсами (а не с классами).
   2. Сложнее понимать и отлаживать из-за использования Reflection.
   3. Производительность ниже, чем у статического прокси.

Преимущества и недостатки статического прокси:

Преимущества:
   1. Код легко читается, объект прокси полностью под контролем.
   2. Не требует использования рефлексии, проще и производительнее.

Недостатки:
   1. Нужно писать прокси вручную для каждого интерфейса.
   2. Не поддерживает динамических сценариев (например, если интерфейс или реализации часто меняются).