    public void testStringToIntConversions() {
        SimpleAction action = new SimpleAction();
        action.setBean(new TestBean());

        stack.push(action);

        Map<String, Object> ognlStackContext = stack.getContext();
        ognlStackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        assertEquals("Conversion should have failed.", OgnlRuntime.NoConversionPossible, converter.convertValue(ognlStackContext, action.getBean(), null, "count", "111.1", int.class));
        stack.pop();

        Map conversionErrors = (Map) stack.getContext().get(ActionContext.CONVERSION_ERRORS);
        assertNotNull(conversionErrors);
        assertTrue(conversionErrors.size() == 1);
    }
