    public void testFindResourceShouldLookupResourceWithCanonicalPath() throws Exception {
        final ServletContext servletContext = createStrictMock(ServletContext.class);  // Verifies method call order

        final URL url = new URL("http://localhost");
        expect(servletContext.getResource("/canonicalized/path.default_extension")).andReturn(url);

        replay(servletContext);

        final ConventionUnknownHandler handler = conventionUnknownHandler(servletContext);
        handler.findResource(defaultResultsByExtension(), "///canonicalized//path");

        verify(servletContext);
    }
