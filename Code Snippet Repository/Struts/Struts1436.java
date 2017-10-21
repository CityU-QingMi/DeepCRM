    public void testShouldUseAutowireStrategyWhenCreatingABeanFromAClassName_setterByName() throws Exception {
        objectFactory.setAlwaysRespectAutowireStrategy(true);

        objectFactory.setAutowireStrategy(AutowireCapableBeanFactory.AUTOWIRE_BY_NAME);
        sac.registerSingleton("actionBean", SimpleAction.class, new MutablePropertyValues());

        SetterByNameBean bean = (SetterByNameBean) objectFactory.buildBean(SetterByNameBean.class, null);

        assertNotNull("Bean should not be null", bean);
        assertNotNull("Action should have been added via DI", bean.getActionBean());
    }
