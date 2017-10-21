    public void testFallBackToDefaultObjectFactoryWhenTheCConstructorDIIsAmbiguous() throws Exception {
        objectFactory.setAlwaysRespectAutowireStrategy(true);
        sac.registerSingleton("firstActionBean", SimpleAction.class, new MutablePropertyValues());
        sac.registerSingleton("secondActionBean", SimpleAction.class, new MutablePropertyValues());

        ConstructorBean bean = (ConstructorBean) objectFactory.buildBean(ConstructorBean.class, null);

        assertNotNull("Bean should have been created using default constructor", bean);
        assertNull("Not expecting this to have been set", bean.getAction());
    }
