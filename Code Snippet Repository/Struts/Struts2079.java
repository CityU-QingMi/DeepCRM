    public void testIfElseIf() throws Exception {
        IfTag ifTag = new IfTag();
        ifTag.setPageContext(pageContext);
        ifTag.setTest("false");

        ElseIfTag elseIfTag1 = new ElseIfTag();
        elseIfTag1.setPageContext(pageContext);
        elseIfTag1.setTest("false");

        ElseIfTag elseIfTag2 = new ElseIfTag();
        elseIfTag2.setPageContext(pageContext);
        elseIfTag2.setTest("true");

        ElseIfTag elseIfTag3 = new ElseIfTag();
        elseIfTag3.setPageContext(pageContext);
        elseIfTag3.setTest("true");

        int r1 = ifTag.doStartTag();
        ifTag.doEndTag();
        int r2 = elseIfTag1.doStartTag();
        elseIfTag1.doEndTag();
        int r3 = elseIfTag2.doStartTag();
        elseIfTag2.doEndTag();
        int r4 = elseIfTag3.doStartTag();
        elseIfTag3.doEndTag();

        assertEquals(TagSupport.SKIP_BODY, r1);
        assertEquals(TagSupport.SKIP_BODY, r2);
        assertEquals(TagSupport.EVAL_BODY_INCLUDE, r3);
        assertEquals(TagSupport.SKIP_BODY, r4);
    }
