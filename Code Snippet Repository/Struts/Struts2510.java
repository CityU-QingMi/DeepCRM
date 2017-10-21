    public void testFindResourceShouldReturnNullAfterTryingEveryExtensionWithoutSuccess() throws Exception {
        final ServletContext servletContext = createStrictMock(ServletContext.class);  // Verifies method call order

        expect(servletContext.getResource("/some/path/which/does/not/exist/for/any/file/with.default_extension"))
                .andReturn(null);
        expect(servletContext.getResource("/some/path/which/does/not/exist/for/any/file/with.non_default_extension"))
                .andReturn(null);
        expect(servletContext.getResource("/some/path/which/does/not/exist/for/any/file/with.some_other_extension"))
                .andReturn(null);

        replay(servletContext);

        final ConventionUnknownHandler handler = conventionUnknownHandler(servletContext);

        final ConventionUnknownHandler.Resource resource = handler.findResource(defaultResultsByExtension(),
                "/some/path/which/does/not/exist/for/any/file/with");

        verify(servletContext);

        assertNull(resource);
    }
