    public void testMethodInjectorWithSecurityEnabled() throws Exception {

        // not needed, already set
        //System.setSecurityManager(new SecurityManager());

        MethodCheck methodCheck = new MethodCheck();

        try {
            c.inject(methodCheck);
            assertEquals(methodCheck.getName(), "Lukasz");
            fail("Exception sould be thrown!");
        } catch (DependencyException expected) {
            // that was expected
        }
    }
