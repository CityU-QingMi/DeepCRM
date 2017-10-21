    public void testObtainingHandlerForRequestByContentType() throws Exception {
        // given
        DefaultContentTypeHandlerManager handlerManager = new DefaultContentTypeHandlerManager();
        handlerManager.setContainer(new DummyContainer("application/json", ""));

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContentType("application/json;charset=UTF-8");
        request.setRequestURI("/index");

        // when
        ContentTypeHandler handler = handlerManager.getHandlerForRequest(request);

        // then
        assertNotNull(handler);
        assertEquals("application/json", handler.getContentType());
        assertEquals("", handler.getExtension());
    }
