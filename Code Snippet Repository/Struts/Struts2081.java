    public void testNestedIfElse1() throws Exception {
        IfTag ifTag = new IfTag();
        ifTag.setPageContext(pageContext);
        ifTag.setTest("true");

        IfTag nestedIfTag = new IfTag();
        nestedIfTag.setPageContext(pageContext);
        nestedIfTag.setTest("true");

        ElseTag elseTag = new ElseTag();
        elseTag.setPageContext(pageContext);

        int r1 = ifTag.doStartTag();
        int r2 = nestedIfTag.doStartTag();
        int r3 = nestedIfTag.doEndTag();
        int r4 = ifTag.doEndTag();
        int r5 = elseTag.doStartTag();
        int r6 = elseTag.doEndTag();

        assertEquals(TagSupport.EVAL_BODY_INCLUDE, r1);
        assertEquals(TagSupport.EVAL_BODY_INCLUDE, r2);
        assertEquals(TagSupport.EVAL_PAGE, r3);
        assertEquals(TagSupport.EVAL_PAGE, r4);
        assertEquals(TagSupport.SKIP_BODY, r5);
        assertEquals(TagSupport.EVAL_PAGE, r6);
    }
