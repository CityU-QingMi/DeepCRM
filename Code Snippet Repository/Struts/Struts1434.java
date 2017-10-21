    public void testShouldUseAutowireStrategyWhenCreatingABeanFromAClassName_constructor() throws Exception {
        objectFactory.setAlwaysRespectAutowireStrategy(true);
        objectFactory.setAutowireStrategy(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR);
        sac.registerSingleton("actionBean", SimpleAction.class, new MutablePropertyValues());

        ConstructorBean bean = (ConstructorBean) objectFactory.buildBean(ConstructorBean.class, null);

        assertNotNull("Bean should not be null", bean);
        assertNotNull("Action should have been added via DI", bean.getAction());
    }
