    protected void validateCounter(Object[] expectedValues) throws JspException {
        List values = new ArrayList();
        try {
            int result = tag.doStartTag();
            assertEquals(result, TagSupport.EVAL_BODY_INCLUDE);
            values.add(stack.getRoot().peek());
        } catch (JspException e) {
            e.printStackTrace();
            fail();
        }

        while (tag.doAfterBody() == TagSupport.EVAL_BODY_AGAIN) {
            values.add(stack.getRoot().peek());
        }

        assertEquals(expectedValues.length, values.size());
        ListUtils.isEqualList(Arrays.asList(expectedValues), values);
    }
