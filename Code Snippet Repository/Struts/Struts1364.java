    public void testPrimitiveSettingWithInvalidValueAddsFieldErrorInNonDevMode() {
        SimpleAction action = new SimpleAction();
        OgnlValueStack stack = createValueStack();
        stack.getContext().put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        stack.setDevMode("false");
        stack.push(action);
        stack.setValue("bar", "3x");

        Map conversionErrors = (Map) stack.getContext().get(ActionContext.CONVERSION_ERRORS);
        assertTrue(conversionErrors.containsKey("bar"));
    }
