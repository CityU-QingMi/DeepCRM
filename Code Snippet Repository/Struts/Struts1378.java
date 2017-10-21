    public void testTop() {
        OgnlValueStack vs = createValueStack();

        Dog dog1 = new Dog();
        dog1.setAge(12);
        dog1.setName("Rover");

        Dog dog2 = new Dog();
        dog2.setAge(1);
        dog2.setName("Jack");
        vs.push(dog1);
        vs.push(dog2);

        assertEquals(dog2, vs.findValue("top"));
        assertEquals("Jack", vs.findValue("top.name"));
    }
