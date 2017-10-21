    public void testGetUriFromActionMapper_justActionAndMethod() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();
        ActionMapping actionMapping = new ActionMapping();
        actionMapping.setMethod("myMethod");
        actionMapping.setName("myActionName");
        actionMapping.setExtension("");
        String uri = mapper.getUriFromActionMapping(actionMapping);

        assertEquals("myActionName!myMethod", uri);
    }
