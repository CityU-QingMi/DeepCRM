    public void testFailFailOnErrorOnInheritedPropertiesWithMethods() {
        OgnlValueStack vs = createValueStack();

        Foo foo = new Foo();
        BarJunior barjr = new BarJunior();
        foo.setBarJunior(barjr);
        vs.push(foo);

        assertNull(barjr.getTitle());
        try {
            vs.findValue("getBarJunior().title2", true);
            fail("should have failed on missing property");
        } catch (Exception e) {
        }
    }
