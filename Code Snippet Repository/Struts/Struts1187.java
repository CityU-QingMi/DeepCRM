    public void testFieldErrorMessageAddedForComplexProperty() {
        SimpleAction action = new SimpleAction();
        action.setBean(new TestBean());

        stack.push(action);

        Map<String, Object> ognlStackContext = stack.getContext();
        ognlStackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);
        ognlStackContext.put(XWorkConverter.CONVERSION_PROPERTY_FULLNAME, "bean.birth");

        String[] value = new String[]{"invalid date"};
        assertEquals("Conversion should have failed.", OgnlRuntime.NoConversionPossible, converter.convertValue(ognlStackContext, action.getBean(), null, "birth", value, Date.class));
        stack.pop();

        Map conversionErrors = (Map) stack.getContext().get(ActionContext.CONVERSION_ERRORS);
        assertNotNull(conversionErrors);
        assertTrue(conversionErrors.size() == 1);
        assertEquals(value, conversionErrors.get("bean.birth"));
    }
