package ru.mikheev.kirill.les5.task;

import ru.mikheev.kirill.les2.task3.DoppelgangerException;
import ru.mikheev.kirill.les2.task3.Person;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.TreeSet;
import java.util.function.Predicate;

/**
 * Класс, отображающий модель картотеки животных
 * @author Kirill Mikheev
 * @version 1.0
 */

public class AnimalDataBank {

    /** хранилище записей о животных, TreeSet автоматически сортирует их*/
    private TreeSet<Animal> animals = new TreeSet<>();


    public AnimalDataBank(){
    }

    /**
     * Добавлет новую запись в картотеку
     * @param newNode объект тип Animal, который нужо добавить
     * @throws DoppelgangerException если такая запись в картотеке уже существует
     */
    public void add(Animal newNode) throws DoppelgangerException {
        if(animals.contains(newNode)){
            throw new DoppelgangerException();
        }
        animals.add(newNode);
    }

    /**
     * Изменяет поля name, owner и weight в объекте Animal с заданным id
     * @param id объекта, который нужно скорректировать
     * @param newName новое имя животного
     * @param newOwner новый владелец животного
     * @param newWeight новый вес животного
     */
    public void editAnimal(Integer id, String newName, Person newOwner, Double newWeight){
        Animal tmp;
        try {
            tmp = animals.stream().filter(a -> a.getId().compareTo(id) == 0).findFirst().get();
        }catch (NoSuchElementException e){
            return;
        }
        tmp.setName(newName);
        tmp.setOwner(newOwner);
        tmp.setWeight(newWeight);
    }

    /**
     * Ищет первое попавшееся животное с данной кличкой
     * @param name кличка животного
     * @return ссылка на его запись, если такого элемента нет, то null
     */
    public Animal findAnimalByName(String name){
        Animal tmp;
        try {
            tmp = animals.stream().filter(a -> a.getName().compareTo(name) == 0).findFirst().get();
        }catch (NoSuchElementException e){
            return null;
        }
        return tmp;
    }

    /**
     * Возвращает отсортированный массив записей о животных в формате String
     * @return отсортированный массив записей о животных в формате String
     */
    @Override
    public String toString() {
        StringBuilder answ = new StringBuilder();
        for (Animal tmp : animals){
            answ.append(tmp.toString() + "\n");
        }
        return answ.toString();
    }
}
