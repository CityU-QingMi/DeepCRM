    public void testSetAfterPush() {
        OgnlValueStack vs = createValueStack();

        Dog d = new Dog();
        d.setName("Rover");
        vs.push(d);

        vs.set("name", "Bill");

        assertEquals("Bill", vs.findValue("name"));

    }
