    public void testFailOnException() {
        OgnlValueStack vs = createValueStack();

        Dog dog = new Dog();
        vs.push(dog);
        try {
            vs.findValue("bite", true);
            fail("Failed to throw exception on EL error");
        } catch (Exception ex) {
            //ok
        }
    }
