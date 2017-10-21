    @Test
    public void testConfigurationClassesExplicit ()
    {
        String[] classNames = {"x.y.z"};

        Server server = new Server();
        server.setAttribute(Configuration.ATTR, classNames);

        //test an explicitly set classnames list overrides that from the server
        WebAppContext wac = new WebAppContext();
        String[] myClassNames = {"a.b.c", "d.e.f"};
        wac.setConfigurationClasses(myClassNames);
        wac.setServer(server);
        String[] names = wac.getConfigurationClasses();
        assertTrue(Arrays.equals(myClassNames, names));


        //test if no explicit classnames, they come from the server
        WebAppContext wac2 = new WebAppContext();
        wac2.setServer(server);
        try
        {
            wac2.loadConfigurations();
        }
        catch(Exception e)
        {
            Log.getRootLogger().ignore(e);
        }
        assertTrue(Arrays.equals(classNames, wac2.getConfigurationClasses()));
    }
