# job4j
# change README.md
2. Ветки в IDEA [#504828 #371861]
В курсе производилась работа в ветках git.

Создание ветки из IDEA.

---
Задание.

1. Создать ветку с именем task_124176. Номер 124176 - это номер задания.

2. Внесите изменения в файл README.md

3. Залейте новую ветку с изменениями на github.

4. Перейдите в новую ветку в интерфейсе github. Откройте закладку коммиты и добавьте ссылку на коммит в эту задачу.

---
Задание.

1. Сделай изменения.

2. Зафиксируйте их в режиме --amend.

3. Загрузите на github изменения в режиме git push origin +master.

4. Оставьте ссылку на коммит. Переведите ответственного на Петра Арсентьева.

---
3.2. Откат изменений. [#307144]
---

6. Запуск нити. Thread#start() 

Уровень : 3. МидлКатегория : 3.1. MultithreadingТопик : 3.1.1. Threads

В этом уроке мы научимся создавать нить исполнения. Напомню, что нить исполнения говорит виртуальной машине, что операторы в этой нити, можно выполнить в многозадачном режиме или даже параллельном режиме. Почему так неоднозначно? Потому что управление нитью передается на усмотрение виртуальной машине. А она в свою очередь может посчитать, что каждую нить можно выполнить, либо в разных процессах (параллельно) или в одном процессе (многозадачно).

В любой программе по умолчанию есть главная нить. В этой нити выполняются операторы из метода main().

Чтобы создать еще одну нить, необходимо воспользоваться классом java.lang.Thread.

Давайте создадим класс ru.job4j.concurrent.ConcurrentOutput.

java
package ru.job4j.concurrent;

public class ConcurrentOutput {
public static void main(String[] args) {
Thread another = new Thread(
() -> System.out.println(Thread.currentThread().getName())
);
another.start();
System.out.println(Thread.currentThread().getName());
}
}

Вывод этой программы может быть разный.

---
Задание.

1. Добавьте новый репозиторий job4j_threads. Все задания из этого раздела выполняйте в этом репозитории.

2. В метод main класса ru.job4j.concurrent.ConcurrentOutput создайте еще один объект Thread. Присвойте имя переменной second.

3. В конструкторе нового объекта задайте вывод на консоль имени новой нити. Для этого воспользуйтесь оператором.

java
Thread.currentThread().getName();
4. Запустите нить на выполнение. Для этого вызовите у объекта метод Thread#start().

5. Запустите метод main несколько раз и убедитесь, что последовательность вывода имен нитей всегда произвольная.

6. Залейте полученный код в репозиторий, оставьте ссылку на коммит.

7. Переведите ответственного на Петра Арсентьева.
