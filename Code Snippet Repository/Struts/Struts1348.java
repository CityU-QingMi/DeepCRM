    public void testBasicSet() {
        OgnlValueStack vs = createValueStack();

        Dog dog = new Dog();
        dog.setAge(12);
        dog.setName("Rover");

        vs.set("dog", dog);
        assertEquals("Rover", vs.findValue("dog.name", String.class));
    }
