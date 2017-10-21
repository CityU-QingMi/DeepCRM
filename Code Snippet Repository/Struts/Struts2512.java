    public void testFindResourceShouldSetCanonicalizedPathOnResource() throws Exception {
        final ServletContext servletContext = createMock(ServletContext.class);

        final URL url = new URL("http://localhost");
        expect(servletContext.getResource("/canonicalized/path.default_extension")).andReturn(url);

        replay(servletContext);

        final ConventionUnknownHandler handler = conventionUnknownHandler(servletContext);

        final ConventionUnknownHandler.Resource resource = handler.findResource(defaultResultsByExtension(),
                "///canonicalized//path");

        assertEquals("/canonicalized/path.default_extension", resource.path);
    }
