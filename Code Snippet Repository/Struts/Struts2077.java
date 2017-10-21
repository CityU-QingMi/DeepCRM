    public void testIfElse1() throws Exception {
        IfTag ifTag = new IfTag();
        ifTag.setPageContext(pageContext);
        ifTag.setTest("true");

        ElseTag elseTag = new ElseTag();
        elseTag.setPageContext(pageContext);

        int r1 = ifTag.doStartTag();
        ifTag.doEndTag();
        int r2 = elseTag.doStartTag();
        elseTag.doEndTag();

        assertEquals(TagSupport.EVAL_BODY_INCLUDE, r1);
        assertEquals(TagSupport.SKIP_BODY, r2);
    }
