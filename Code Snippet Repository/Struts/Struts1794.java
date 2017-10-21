    public void testDropExtensionEmbeddedDot() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setExtensions("action,,");

        String name = mapper.dropExtension("/foo/bar-1.0/baz.action", new ActionMapping());
        assertTrue("Name not right: "+name, "/foo/bar-1.0/baz".equals(name));

        name = mapper.dropExtension("/foo/bar-1.0/baz", new ActionMapping());
        assertTrue("Name not right: "+name, "/foo/bar-1.0/baz".equals(name));
    }
