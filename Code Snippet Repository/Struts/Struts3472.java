    public void testSlashesInNameWithWildcardsHitsCache() throws Exception {
        ActionProxy baseActionProxy = actionProxyFactory.createActionProxy("oval", "simpleFieldsXML/test", null, null);
        baseActionProxy.execute();

        ActionProxy baseActionProxy2 = actionProxyFactory.createActionProxy("oval", "simpleFieldsXML/test2", null, null);
        baseActionProxy2.execute();

        DummyDefaultOValValidationManager manager = (DummyDefaultOValValidationManager) container.getInstance(OValValidationManager.class);
        assertEquals(1, manager.getCache().size());
    }
