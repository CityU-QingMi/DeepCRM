    public void testFailOnMissingMethod() {
        OgnlValueStack vs = createValueStack();

        Dog dog = new Dog();
        vs.push(dog);
        try {
            vs.findValue("someprop()", true);
            fail("Failed to throw exception on EL missing method");
        } catch (Exception ex) {
            //ok
        }
    }
