    public void testInvocationOrder() throws ConfigurationException, NoSuchMethodException {
        SomeUnknownHandler uh1 = new SomeUnknownHandler();
        uh1.setActionMethodResult("uh1");

        SomeUnknownHandler uh2 = new SomeUnknownHandler();
        uh2.setActionMethodResult("uh2");

        UnknownHandlerManagerMock uhm = new UnknownHandlerManagerMock();
        uhm.addUnknownHandler(uh1);
        uhm.addUnknownHandler(uh2);

        //should pick the first one
        assertEquals("uh1", uhm.handleUnknownMethod(null, null));

        //should pick the second one
        uh1.setActionMethodResult(null);
        assertEquals("uh2", uhm.handleUnknownMethod(null, null));

        //should not pick any
        uh1.setActionMethodResult(null);
        uh2.setActionMethodResult(null);
        try {
            uhm.handleUnknownMethod(null, null);
            fail("Should throw exception!");
        } catch (NoSuchMethodException e) {
            assertTrue(true);
        }
    }
