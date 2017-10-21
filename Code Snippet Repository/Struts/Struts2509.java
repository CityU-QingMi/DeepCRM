    public void testHandleUnknownActionPointToIndex() throws Exception {
        // given
        ServletContext servletContext = createStrictMock(ServletContext.class);
        expect(servletContext.getResource("/does-not-exist.jsp")).andReturn(null);
        expect(servletContext.getResource("/does-not-exist/index.jsp")).andReturn(null);
        replay(servletContext);

        ConventionUnknownHandler handler = conventionUnknownHandler(servletContext);

        // when
        ActionConfig config = handler.handleUnknownAction("", "/does-not-exist");

        // then
        assertNotNull(config);
        assertEquals("", config.getPackageName());
        assertEquals("index", config.getName());
    }
