    public void testFieldInjectorWithSecurityEnabled() throws Exception {

        System.setSecurityManager(new SecurityManager());

        FieldCheck fieldCheck = new FieldCheck();

        try {
            c.inject(fieldCheck);
            assertEquals(fieldCheck.getName(), "Lukasz");
            fail("Exception should be thrown!");
        } catch (DependencyException expected) {
            // that was expected
        }
    }
