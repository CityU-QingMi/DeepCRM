    public void testObtainingHandlerForRequestByExtension() throws Exception {
        // given
        DefaultContentTypeHandlerManager handlerManager = new DefaultContentTypeHandlerManager();
        handlerManager.setContainer(new DummyContainer("text/html", "json"));

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContentType("application/json;charset=UTF-8");
        request.setRequestURI("/index.json");

        // when
        ContentTypeHandler handler = handlerManager.getHandlerForRequest(request);

        // then
        assertNotNull(handler);
        assertEquals("text/html", handler.getContentType());
        assertEquals("json", handler.getExtension());
    }
