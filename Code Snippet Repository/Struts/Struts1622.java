    public void testVisitorChildValidation() throws Exception {
        validate("visitorChildValidation");
        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();
        assertEquals(5, fieldErrors.size());
        assertTrue(!fieldErrors.containsKey("bean.count"));
        assertTrue(fieldErrors.containsKey("bean.name"));
        assertTrue(fieldErrors.containsKey("bean.birth"));

        assertTrue(fieldErrors.containsKey("bean.child.name"));
        assertTrue(fieldErrors.containsKey("bean.child.birth"));
        
        //the error from the action should be there too
        assertTrue(fieldErrors.containsKey("context"));
    }
