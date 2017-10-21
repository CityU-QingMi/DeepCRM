    public void testFieldErrorMessageAddedWhenConversionFailsOnModelDriven() {
        ModelDrivenAction action = new ModelDrivenAction();
        stack.push(action);
        stack.push(action.getModel());

        Map<String, Object> ognlStackContext = stack.getContext();
        ognlStackContext.put(XWorkConverter.REPORT_CONVERSION_ERRORS, Boolean.TRUE);

        String[] value = new String[]{"invalid date"};
        assertEquals("Conversion should have failed.", OgnlRuntime.NoConversionPossible, converter.convertValue(ognlStackContext, action, null, "birth", value, Date.class));
        stack.pop();
        stack.pop();

        Map conversionErrors = (Map) ognlStackContext.get(ActionContext.CONVERSION_ERRORS);
        assertNotNull(conversionErrors);
        assertEquals(1, conversionErrors.size());
        assertNotNull(conversionErrors.get("birth"));
        assertEquals(value, conversionErrors.get("birth"));
    }
