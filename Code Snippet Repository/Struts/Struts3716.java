    public void testWithSpringContext() throws Exception {
        Container container = EasyMock.createNiceMock(Container.class);
        EasyMock.replay(container);

        ConfigurableWebApplicationContext ac = new XmlWebApplicationContext();
        ServletContext msc = new MockServletContext();
        msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ac);
        ac.setServletContext(msc);
        ac.setConfigLocations(new String[] {"org/apache/struts2/spring/StrutsSpringObjectFactoryTest-applicationContext.xml"});
        ac.refresh();
        StrutsSpringObjectFactory fac = new StrutsSpringObjectFactory("constructor", null, null, null, msc, null, container);

        assertEquals(AutowireCapableBeanFactory.AUTOWIRE_CONSTRUCTOR, fac.getAutowireStrategy());
    }
