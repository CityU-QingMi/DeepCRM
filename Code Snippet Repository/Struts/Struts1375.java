    public void testSettingDogGender() {
        OgnlValueStack vs = createValueStack();

        Dog dog = new Dog();
        vs.push(dog);

        vs.setValue("male", "false");

        assertEquals(false, dog.isMale());
    }
