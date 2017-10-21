    public void testSetAutowireStrategy() throws Exception {
        assertEquals(objectFactory.getAutowireStrategy(), AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);

        objectFactory.setAutowireStrategy(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);

        sac.getBeanFactory().registerSingleton("bean", new TestBean());
        TestBean bean = (TestBean) sac.getBean("bean");

        sac.registerPrototype("simple-action", SimpleAction.class, new MutablePropertyValues());

        ActionConfig actionConfig = new ActionConfig.Builder("jim", "bob", "simple-action").build();
        SimpleAction simpleAction = (SimpleAction) objectFactory.buildBean(actionConfig.getClassName(), null);
        objectFactory.autoWireBean(simpleAction);
        assertEquals(simpleAction.getBean(), bean);
    }
