    public void testPrimitiveSettingWithInvalidValueAddsFieldErrorInDevMode() {
        SimpleAction action = new SimpleAction();
        OgnlValueStack stack = createValueStack();
        stack.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        stack.setDevMode("true");
        stack.push(action);

        try {
            stack.setValue("bar", "3x");
            fail("Attempt to set 'bar' int property to '3x' should result in RuntimeException");
        }
        catch (RuntimeException re) {
            assertTrue(true);
        }

        Map conversionErrors = (Map) stack.getContext().get(ActionContext.CONVERSION_ERRORS);
        assertTrue(conversionErrors.containsKey("bar"));
    }
