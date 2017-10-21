    public void testVisitorChildConversionValidation() throws Exception {
        //add conversion error
        Map<String, Object> conversionErrors = new HashMap<>();
        conversionErrors.put("bean.child.count", "bar");
        ActionContext.getContext().setConversionErrors(conversionErrors);

        validate("visitorChildValidation");
        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertEquals(6, fieldErrors.size());
        assertTrue(!fieldErrors.containsKey("bean.count"));
        assertTrue(fieldErrors.containsKey("bean.name"));
        assertTrue(fieldErrors.containsKey("bean.birth"));

        assertTrue(fieldErrors.containsKey("bean.child.name"));
        assertTrue(fieldErrors.containsKey("bean.child.birth"));

        //the error from the action should be there too
        assertTrue(fieldErrors.containsKey("context"));

        //nested visitor conversion error
        assertTrue(fieldErrors.containsKey("bean.child.count"));
    }
