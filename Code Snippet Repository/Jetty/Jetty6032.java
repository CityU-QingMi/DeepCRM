    @Test
    public void ordering() throws Exception
    {
        Path testWebappDir = MavenTestingUtils.getProjectDirPath("src/test/webapp");
        Resource webapp = new PathResource(testWebappDir);
        WebAppContext context = new WebAppContext();
        context.setBaseResource(webapp);
        context.setContextPath("/test");
        new WebInfConfiguration().preConfigure(context);
        assertEquals(Arrays.asList("acme.jar", "alpha.jar", "omega.jar"),
            context.getMetaData().getWebInfJars().stream().map(r -> r.getURI().toString().replaceFirst(".+/", "")).collect(Collectors.toList()));
    }
