    @Test
    public void testServerAndSystemClassesOverride() throws Exception
    {
        File srcXml = MavenTestingUtils.getTestResourceFile("context-binding-test-1.xml");
        File destXml = new File(jetty.getJettyHome(),"context-binding-test-1.xml");
        IO.copy(srcXml,destXml);

        PathAssert.assertFileExists("Context Binding XML",destXml);

        jetty.addConfiguration("binding-test-contexts-1.xml");
        jetty.load();
        jetty.start();

        List<WebAppContext> contexts = jetty.getWebAppContexts();
        Assert.assertThat("List of Contexts", contexts, hasSize(greaterThan(0)));

        WebAppContext context = contexts.get(0);

        Assert.assertNotNull("Context should not be null",context);
        String defaultClasses[] = context.getDefaultServerClasses();
        String currentClasses[] = context.getServerClasses();

        String addedClass = "org.eclipse.foo."; // What was added by the binding
        Assert.assertThat("Default Server Classes",addedClass,not(isIn(defaultClasses)));
        Assert.assertThat("Current Server Classes",addedClass,isIn(currentClasses));

        //  boolean jndiPackage = false;

        // this test overrides and we removed the jndi from the list so it
        // should test false
        //        for (String entry : context.getSystemClasses())
        //        {
        //            if ("org.eclipse.jetty.jndi.".equals(entry))
        //            {
        //                jndiPackage = true;
        //            }
        //        }
        //
        //        Assert.assertFalse(jndiPackage);
    }
