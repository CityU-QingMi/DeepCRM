    public void testHandlerOverride() {
        Mock mockHandlerXml = new Mock(ContentTypeHandler.class);
        mockHandlerXml.matchAndReturn("getExtension", "xml");
        mockHandlerXml.matchAndReturn("getContentType", "application/xml");
        mockHandlerXml.matchAndReturn("toString", "xml");
        Mock mockHandlerJson = new Mock(ContentTypeHandler.class);
        mockHandlerJson.matchAndReturn("getExtension", "json");
        mockHandlerJson.matchAndReturn("getContentType", "application/javascript");
        mockHandlerJson.matchAndReturn("toString", "json");
        Mock mockHandlerXmlOverride = new Mock(ContentTypeHandler.class);
        mockHandlerXmlOverride.matchAndReturn("getExtension", "xml");
        mockHandlerXmlOverride.matchAndReturn("toString", "xmlOverride");
        mockHandlerXmlOverride.matchAndReturn("getContentType", "application/xml");

        Mock mockContainer = new Mock(Container.class);
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(ContentTypeHandler.class), C.eq("xmlOverride")), mockHandlerXmlOverride.proxy());
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(ContentTypeHandler.class), C.eq("xml")), mockHandlerXml.proxy());
        mockContainer.matchAndReturn("getInstance", C.args(C.eq(ContentTypeHandler.class), C.eq("json")), mockHandlerJson.proxy());
        mockContainer.expectAndReturn("getInstanceNames", C.args(C.eq(ContentTypeHandler.class)), new HashSet(Arrays.asList("xml", "xmlOverride", "json")));

        mockContainer.matchAndReturn("getInstance", C.args(C.eq(String.class),
                C.eq(ContentTypeHandlerManager.STRUTS_REST_HANDLER_OVERRIDE_PREFIX+"xml")), "xmlOverride");
        mockContainer.expectAndReturn("getInstance", C.args(C.eq(String.class),
                C.eq(ContentTypeHandlerManager.STRUTS_REST_HANDLER_OVERRIDE_PREFIX+"json")), null);
        
        DefaultContentTypeHandlerManager mgr = new DefaultContentTypeHandlerManager();
        mgr.setContainer((Container) mockContainer.proxy());

        Map<String,ContentTypeHandler> handlers = mgr.handlersByExtension;
        assertNotNull(handlers);
        assertEquals(2, handlers.size());
        assertEquals(mockHandlerXmlOverride.proxy(), handlers.get("xml"));
        assertEquals(mockHandlerJson.proxy(), handlers.get("json"));
    }
