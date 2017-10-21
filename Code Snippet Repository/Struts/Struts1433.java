    public void testShouldUseConstructorBasedInjectionWhenCreatingABeanFromAClassName() throws Exception {
        SpringObjectFactory factory = (SpringObjectFactory) objectFactory;
        objectFactory.setAlwaysRespectAutowireStrategy(false);
        sac.registerSingleton("actionBean", SimpleAction.class, new MutablePropertyValues());

        ConstructorBean bean = (ConstructorBean) factory.buildBean(ConstructorBean.class, null);

        assertNotNull("Bean should not be null", bean);
        assertNotNull("Action should have been added via DI", bean.getAction());
    }
