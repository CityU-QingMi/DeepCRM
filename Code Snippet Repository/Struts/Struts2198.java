    public void testDynamicAttribute() throws Exception {
        createAction();

        AnchorTag tag = createTag();
        tag.setHref("a");

        tag.setDynamicAttribute("uri", "dynAttrName", "dynAttrValue");

        tag.doStartTag();
        tag.doEndTag();

        verifyResource("Anchor-2.txt");
    }
