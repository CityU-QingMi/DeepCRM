    @Test
    public void testObtainJmxWebAppState() throws Exception
    {
        ObjectName webappName = new ObjectName("org.eclipse.jetty.webapp:context=jmx-webapp,type=webappcontext,id=0");

        String contextPath = getStringAttribute(webappName, "contextPath");
        assertThat("Context Path", contextPath, is("/jmx-webapp"));

        String displayName = getStringAttribute(webappName, "displayName");
        assertThat("Display Name", displayName, is("Test JMX WebApp"));
    }
