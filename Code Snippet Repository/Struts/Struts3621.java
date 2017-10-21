    public void testObtainingHandlerForRequestWithEncoding() throws Exception {
        // given
        DefaultContentTypeHandlerManager handlerManager = new DefaultContentTypeHandlerManager();
        handlerManager.setContainer(new DummyContainer("application/json;charset=UTF-8", null));

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContentType("application/json;charset=UTF-8");

        // when
        ContentTypeHandler handler = handlerManager.getHandlerForRequest(request);

        // then
        assertNotNull(handler);
        assertEquals("application/json;charset=UTF-8", handler.getContentType());
    }
