    @Test
    public void testScanTypes()
    throws Exception
    {
        File web25 = MavenTestingUtils.getTestResourceFile("web25.xml");
        File web31 = MavenTestingUtils.getTestResourceFile("web31.xml");
        File web31false = MavenTestingUtils.getTestResourceFile("web31false.xml");
        
        //test a 2.5 webapp will not look for fragments by default
        MetaInfConfiguration meta25 = new TestableMetaInfConfiguration(MetaInfConfiguration.__allScanTypes,
                                                                       Arrays.asList(MetaInfConfiguration.METAINF_TLDS, MetaInfConfiguration.METAINF_RESOURCES));
        WebAppContext context25 = new WebAppContext();
        context25.getMetaData().setWebXml(Resource.newResource(web25));
        context25.getServletContext().setEffectiveMajorVersion(2);
        context25.getServletContext().setEffectiveMinorVersion(5);
        meta25.preConfigure(context25);
        
        //test a 2.5 webapp will look for fragments if configurationDiscovered==true
        MetaInfConfiguration meta25b = new TestableMetaInfConfiguration(MetaInfConfiguration.__allScanTypes,
                                                                        MetaInfConfiguration.__allScanTypes);
        WebAppContext context25b = new WebAppContext();
        context25b.setConfigurationDiscovered(true);
        context25b.getMetaData().setWebXml(Resource.newResource(web25));
        context25b.getServletContext().setEffectiveMajorVersion(2);
        context25b.getServletContext().setEffectiveMinorVersion(5);
        meta25b.preConfigure(context25b);
        
        //test a 3.x metadata-complete webapp will not look for fragments
        MetaInfConfiguration meta31 = new TestableMetaInfConfiguration(MetaInfConfiguration.__allScanTypes,
                                                                       Arrays.asList(MetaInfConfiguration.METAINF_TLDS, MetaInfConfiguration.METAINF_RESOURCES));
        WebAppContext context31 = new WebAppContext();
        context31.getMetaData().setWebXml(Resource.newResource(web31));
        context31.getServletContext().setEffectiveMajorVersion(3);
        context31.getServletContext().setEffectiveMinorVersion(1);
        meta31.preConfigure(context31);

        //test a 3.x non metadata-complete webapp will look for fragments
        MetaInfConfiguration meta31false = new TestableMetaInfConfiguration(MetaInfConfiguration.__allScanTypes,
                                                                            MetaInfConfiguration.__allScanTypes);
        WebAppContext context31false = new WebAppContext();
        context31false.setConfigurationDiscovered(true);
        context31false.getMetaData().setWebXml(Resource.newResource(web31false));
        context31false.getServletContext().setEffectiveMajorVersion(3);
        context31false.getServletContext().setEffectiveMinorVersion(1);
        meta31false.preConfigure(context31false);
    }
