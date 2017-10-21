    public void testSetBeforePush() {
        OgnlValueStack vs = createValueStack();

        vs.set("name", "Bill");
        Dog d = new Dog();
        d.setName("Rover");
        vs.push(d);

        assertEquals("Rover", vs.findValue("name"));

    }
