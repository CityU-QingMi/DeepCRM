    public void testShouldUseAutowireStrategyWhenCreatingABeanFromAClassName_setterByType() throws Exception {
        objectFactory.setAlwaysRespectAutowireStrategy(true);

        objectFactory.setAutowireStrategy(AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE);
        sac.registerSingleton("actionBean", SimpleAction.class, new MutablePropertyValues());

        SetterByTypeBean bean = (SetterByTypeBean) objectFactory.buildBean(SetterByTypeBean.class, null);

        assertNotNull("Bean should not be null", bean);
        assertNotNull("Action should have been added via DI", bean.getAction());
    }
