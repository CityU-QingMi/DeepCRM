    public void testContextIsOverriddenByContextParamInValidationXML() throws Exception {
        validate("visitorValidationAlias");
        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertEquals(3, fieldErrors.size());
        assertTrue(fieldErrors.containsKey("bean.count"));
        assertTrue(fieldErrors.containsKey("bean.name"));
        assertTrue(!fieldErrors.containsKey("bean.birth"));

        //the error from the action should be there too
        assertTrue(fieldErrors.containsKey("context"));
    }
