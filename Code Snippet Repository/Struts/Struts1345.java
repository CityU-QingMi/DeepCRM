    public void testDoesNotFailOnNonActionObjects() {
        //if a value is not found, then it will check for missing properties
        //it needs to check in all objects in the stack, not only actions, see WW-3306
        OgnlValueStack vs = createValueStack();

        Dog dog = new Dog();
        dog.setHates(null);
        vs.push(dog);
        vs.findValue("hates", true);
    }
