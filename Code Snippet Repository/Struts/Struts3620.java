    public void testHandleRequestContentType() throws IOException {

        Mock mockHandlerForm = new Mock(ContentTypeHandler.class);
        mockHandlerForm.matchAndReturn("getExtension", null);
        mockHandlerForm.matchAndReturn("getContentType", "application/x-www-form-urlencoded");
        mockHandlerForm.matchAndReturn("toString", "x-www-form-urlencoded");

        Mock mockHandlerJson = new Mock(ContentTypeHandler.class);
        mockHandlerJson.matchAndReturn("getExtension", "json");
        mockHandlerJson.matchAndReturn("getContentType", "application/javascript");
        mockHandlerJson.matchAndReturn("toString", "json");

        Mock mockContainer = new Mock(Container.class);
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(ContentTypeHandler.class), C.eq("x-www-form-urlencoded")), mockHandlerForm.proxy());
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(ContentTypeHandler.class), C.eq("json")), mockHandlerJson.proxy());
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(String.class), C.eq("struts.rest.handlerOverride.json")), null);
        mockContainer.expectAndReturn("getInstanceNames", C.args(C.eq(ContentTypeHandler.class)), new HashSet(Arrays.asList("x-www-form-urlencoded", "json")));

        mockRequest.setContentType(FormUrlEncodedHandler.CONTENT_TYPE);
        mockRequest.setContent("a=1&b=2".getBytes("UTF-8"));
        mgr.setContainer((Container) mockContainer.proxy());
        ContentTypeHandler handler = mgr.getHandlerForRequest(mockRequest);

        assertEquals("application/x-www-form-urlencoded", handler.getContentType());
    }
