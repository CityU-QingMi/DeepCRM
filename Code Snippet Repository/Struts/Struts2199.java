    public void testDynamicAttributeAsExpression() throws Exception {
        createAction();

        AnchorTag tag = createTag();
        tag.setHref("a");

        tag.setDynamicAttribute("uri", "placeholder", "%{foo}");

        tag.doStartTag();
        tag.doEndTag();

        verifyResource("Anchor-3.txt");
    }
