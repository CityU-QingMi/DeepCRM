    @Before
    public void setupEnvironment() throws Exception
    {
        testdir.ensureEmpty();
        Resource.setDefaultUseCaches(false);
        
        jetty = new XmlConfiguredJetty(testdir);
        jetty.addConfiguration("jetty.xml");
        jetty.addConfiguration("jetty-http.xml");
        jetty.addConfiguration("jetty-deploymgr-contexts.xml");

        // Should not throw an Exception
        jetty.load();

        // Start it
        jetty.start();

        // monitor tick
        DeploymentManager dm = jetty.getServer().getBean(DeploymentManager.class);
        for (AppProvider provider : dm.getAppProviders())
        {
            if (provider instanceof ScanningAppProvider)
            {
                _providers++;
                ((ScanningAppProvider)provider).addScannerListener(new Scanner.ScanListener()
                {
                    public void scan()
                    {
                        _scans.incrementAndGet();
                    }
                });
            }
        }

    }
