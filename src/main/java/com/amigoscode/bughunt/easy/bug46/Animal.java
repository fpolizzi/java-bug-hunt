package com.amigoscode.bughunt.easy.bug46;

public class Animal {

    private final String sound;
    private final String species;

    public Animal(String species, String sound) {
        this.species = species;
        this.sound = sound;
    }

    protected String computeSound() {
        return "generic animal noise";
    }

    public String sound() {
        return sound;
    }

    public String species() {
        return species;
    }

    public String describe() {
        return species + " says: " + sound;
    }

    public static class Dog extends Animal {

        private final String breed;

        public Dog(String breed) {
            super("dog", breed.toUpperCase() + " bark!");
            this.breed = breed;
        }

        @Override
        protected String computeSound() {
            return breed.toUpperCase() + " bark!";
        }

        public String breed() {
            return breed;
        }
    }
}
