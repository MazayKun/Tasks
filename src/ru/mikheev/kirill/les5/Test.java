package ru.mikheev.kirill.les5;

import ru.mikheev.kirill.les2.task3.DoppelgangerException;
import ru.mikheev.kirill.les2.task3.PersonGenerator;
import ru.mikheev.kirill.les5.task.Animal;
import ru.mikheev.kirill.les5.task.AnimalDataBank;

/**
 * Файл мейн для тестирования заданий по 5 занятию
 * @author Kirill Mikheev
 * @version 1.0
 */

public class Test {
    public static void main(String[] argc) {
        System.out.println("Puff");

        AnimalDataBank animalDataBank = new AnimalDataBank();
        for (int i = 0; i < 100; i++){
            try {
                animalDataBank.add(Animal.generateAnimal());
            } catch (DoppelgangerException e) {
                System.out.println("Плохо сгенерил");
            }
        }
        animalDataBank.editAnimal(50, "Свинослон", PersonGenerator.generatePerson(), 100000000d);
        System.out.println(animalDataBank.toString());
        System.out.println(animalDataBank.findAnimalByName("ghig"));
    }
}
