     public void testSuccessFailOnErrorOnInheritedPropertiesWithMethods() {
        //this shuld not fail as the property is defined on a parent class
        OgnlValueStack vs = createValueStack();

        Foo foo = new Foo();
        BarJunior barjr = new BarJunior();
        foo.setBarJunior(barjr);
        vs.push(foo);

        assertNull(barjr.getTitle());
        vs.findValue("getBarJunior().title", true);
    }
