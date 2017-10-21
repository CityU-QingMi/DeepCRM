    public void testFieldErrorMessageAddedWhenConversionFails() {
        SimpleAction action = new SimpleAction();
        action.setDate(null);

        stack.push(action);

        Map<String, Object> ognlStackContext = stack.getContext();
        ognlStackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        String[] value = new String[]{"invalid date"};
        assertEquals("Conversion should have failed.", OgnlRuntime.NoConversionPossible, converter.convertValue(ognlStackContext, action, null, "date", value, Date.class));
        stack.pop();

        Map conversionErrors = (Map) ognlStackContext.get(ActionContext.CONVERSION_ERRORS);
        assertNotNull(conversionErrors);
        assertEquals(1, conversionErrors.size());
        assertNotNull(conversionErrors.get("date"));
        assertEquals(value, conversionErrors.get("date"));
    }
