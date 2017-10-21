    public void testTagAttributeExclusion() throws Exception {
        FormTag tag = new FormTag();
        tag.setPageContext(pageContext);

        tag.setDynamicAttribute("uri://some.uri", "includeContext", false);

        tag.doStartTag();

        assertTrue(tag.includeContext);
    }
