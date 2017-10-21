    public void testObjectSettingWithInvalidValueDoesNotCauseSetCalledWithNull() {
        SimpleAction action = new SimpleAction();
        action.setBean(new TestBean());
        OgnlValueStack stack = createValueStack();
        stack.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        stack.push(action);
        try {
            stack.setValue("bean", "foobar", true);
            fail("Should have thrown a type conversion exception");
        } catch (XWorkException e) {
            // expected
        }

        Map conversionErrors = (Map) stack.getContext().get(ActionContext.CONVERSION_ERRORS);
        assertTrue(conversionErrors.containsKey("bean"));
        assertNotNull(action.getBean());
    }
