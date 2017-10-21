    public void testIfIsFalseElseIfIsTrue() throws Exception {
        stack.getContext().put(If.ANSWER, Boolean.FALSE);

        ElseIfTag tag = new ElseIfTag();
        tag.setPageContext(pageContext);
        tag.setTest("true");

        int result = tag.doStartTag();
        tag.doEndTag();

        assertEquals(result, TagSupport.EVAL_BODY_INCLUDE);
    }
