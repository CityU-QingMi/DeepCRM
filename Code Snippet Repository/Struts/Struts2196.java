    public void testSimple() throws Exception {
        createAction();

        AnchorTag tag = createTag();
        tag.setHref("a");
        tag.doStartTag();
        tag.doEndTag();

        verifyResource("href-1.txt");
    }
