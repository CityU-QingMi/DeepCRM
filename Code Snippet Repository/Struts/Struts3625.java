    public void testObtainingHandlerForResponseByAcceptHeader() throws Exception {

        // given
        final DefaultContentTypeHandlerManager handlerManager = new DefaultContentTypeHandlerManager();
        handlerManager.setContainer(new DummyContainer("application/json", "json"));

        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setContentType("application/json;charset=UTF-8");
        request.addHeader("accept","application/json;charset=UTF-8");
        request.setRequestURI("/index");

        final MockHttpServletResponse response = new MockHttpServletResponse();
        response.setContentType("application/json;charset=UTF-8");

        // when
        ContentTypeHandler handler = handlerManager.getHandlerForResponse(request,response);

        // then
        assertNotNull(handler);
        assertEquals("application/json", handler.getContentType());
        assertEquals("json", handler.getExtension());
    }
