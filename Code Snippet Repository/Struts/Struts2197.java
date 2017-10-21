    public void testSimpleBadQuote() throws Exception {
        createAction();

        AnchorTag tag = createTag();
        tag.setHref("a\"");
        tag.doStartTag();
        tag.doEndTag();

        verifyResource("href-2.txt");
    }
