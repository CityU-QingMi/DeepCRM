    public void testIfElse2() throws Exception {
        IfTag ifTag = new IfTag();
        ifTag.setPageContext(pageContext);
        ifTag.setTest("false");

        ElseTag elseTag = new ElseTag();
        elseTag.setPageContext(pageContext);

        int r1 = ifTag.doStartTag();
        ifTag.doEndTag();
        int r2 = elseTag.doStartTag();
        elseTag.doEndTag();

        assertEquals(TagSupport.SKIP_BODY, r1);
        assertEquals(TagSupport.EVAL_BODY_INCLUDE, r2);
    }
