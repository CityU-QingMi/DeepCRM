    public void testTestFalse() {
        stack.getContext().put(If.ANSWER, new Boolean(false));

        int result = 0;

        try {
            elseTag.setPageContext(pageContext);
            result = elseTag.doStartTag();
            elseTag.doEndTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }
        assertEquals(TagSupport.EVAL_BODY_INCLUDE, result);
    }
