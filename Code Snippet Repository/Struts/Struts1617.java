    public void testArrayValidation() throws Exception {
        TestBean[] beanArray = action.getTestBeanArray();
        TestBean testBean = beanArray[0];
        testBean.setName("foo");
        validate("validateArray");

        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        //4 errors for the array, one for context
        assertEquals(5, fieldErrors.size());
        assertTrue(fieldErrors.containsKey("testBeanArray[1].name"));

        //the error from the action should be there too
        assertTrue(fieldErrors.containsKey("context"));

        List<String> errors = fieldErrors.get("testBeanArray[1].name");
        assertEquals(1, errors.size());
        errors = fieldErrors.get("testBeanArray[2].name");
        assertEquals(1, errors.size());
        errors = fieldErrors.get("testBeanArray[3].name");
        assertEquals(1, errors.size());
        errors = fieldErrors.get("testBeanArray[4].name");
        assertEquals(1, errors.size());
    }
