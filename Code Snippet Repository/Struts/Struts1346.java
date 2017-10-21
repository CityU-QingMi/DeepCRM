    public void testFailOnMissingNestedProperty() {
        OgnlValueStack vs = createValueStack();

        Dog dog = new Dog();
        dog.setHates(new Cat());
        vs.push(dog);
        try {
            vs.findValue("hates.someprop", true);
            fail("Failed to throw exception on EL missing nested property");
        } catch (Exception ex) {
            //ok
        }
    }
