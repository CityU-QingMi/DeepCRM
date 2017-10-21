    private void iterateThreeStrings() {
        int result = 0;

        try {
            result = tag.doStartTag();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(result, TagSupport.EVAL_BODY_INCLUDE);
        assertEquals("test1", stack.getRoot().peek());
        assertEquals(4, stack.size());

        try {
            result = tag.doAfterBody();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(result, TagSupport.EVAL_BODY_AGAIN);
        assertEquals("test2", stack.getRoot().peek());
        assertEquals(4, stack.size());

        try {
            result = tag.doAfterBody();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(result, TagSupport.EVAL_BODY_AGAIN);
        assertEquals("test3", stack.getRoot().peek());
        assertEquals(4, stack.size());

        try {
            result = tag.doAfterBody();
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        assertEquals(result, TagSupport.SKIP_BODY);
        assertEquals(3, stack.size());
    }
