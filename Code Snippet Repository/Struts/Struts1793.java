    public void testDropExtensionWhenBlank() throws Exception {
        DefaultActionMapper mapper = new DefaultActionMapper();
        mapper.setExtensions("action,,");
        String name = mapper.dropExtension("foo.action", new ActionMapping());
        assertTrue("Name not right: "+name, "foo".equals(name));
        name = mapper.dropExtension("foo", new ActionMapping());
        assertTrue("Name not right: "+name, "foo".equals(name));
        assertNull(mapper.dropExtension("foo.bar", new ActionMapping()));
        assertNull(mapper.dropExtension("foo.", new ActionMapping()));
    }
