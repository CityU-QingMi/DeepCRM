    public void testGetUriFromActionMapperWhenBlankExtension() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setExtensions(",,");
        ActionMapping actionMapping = new ActionMapping();
        actionMapping.setMethod("myMethod");
        actionMapping.setName("myActionName");
        actionMapping.setNamespace("/myNamespace");
        String uri = mapper.getUriFromActionMapping(actionMapping);

        assertEquals("/myNamespace/myActionName!myMethod", uri);
    }
