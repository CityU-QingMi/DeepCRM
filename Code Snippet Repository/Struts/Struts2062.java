    public void testIfIsTrueElseIfIsFalse() throws Exception {
        stack.getContext().put(If.ANSWER, Boolean.TRUE);

        ElseIfTag tag = new ElseIfTag();
        tag.setPageContext(pageContext);
        tag.setTest("false");

        int result = tag.doStartTag();
        tag.doEndTag();

        assertEquals(result, TagSupport.SKIP_BODY);
    }
