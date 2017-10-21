    public void testTypeConversionError() {
        TestBean bean = new TestBean();
        OgnlValueStack stack = createValueStack();
        stack.push(bean);
        stack.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        try {
            stack.setValue("count", "a", true);
            fail("Should have thrown a type conversion exception");
        } catch (XWorkException e) {
            // expected
        }

        Map conversionErrors = (Map) stack.getContext().get(ActionContext.CONVERSION_ERRORS);
        assertTrue(conversionErrors.containsKey("count"));
    }
