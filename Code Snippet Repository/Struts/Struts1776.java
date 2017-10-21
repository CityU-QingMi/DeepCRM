    public void testParseNameAndNamespace_AllowSlashes() throws Exception {
        ActionMapping actionMapping = new ActionMapping();

        DefaultActionMapper defaultActionMapper = new DefaultActionMapper();
        defaultActionMapper.setSlashesInActionNames("true");
        defaultActionMapper.parseNameAndNamespace("/foo/someAction", actionMapping, configManager);

        assertEquals(actionMapping.getName(), "foo/someAction");
        assertEquals(actionMapping.getNamespace(), "");
    }
