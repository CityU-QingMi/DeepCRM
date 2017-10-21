    public void testCollectionValidation() throws Exception {
        List testBeanList = action.getTestBeanList();
        TestBean testBean = (TestBean) testBeanList.get(0);
        testBean.setName("foo");
        validate("validateList");

        assertTrue(action.hasFieldErrors());

        Map<String, List<String>> fieldErrors = action.getFieldErrors();

        //4 for the list, 1 for context
        assertEquals(5, fieldErrors.size());
        assertTrue(fieldErrors.containsKey("testBeanList[1].name"));

        //the error from the action should be there too
        assertTrue(fieldErrors.containsKey("context"));

        List<String> errors = fieldErrors.get("testBeanList[1].name");
        assertEquals(1, errors.size());
        errors = fieldErrors.get("testBeanList[2].name");
        assertEquals(1, errors.size());
        errors = fieldErrors.get("testBeanList[3].name");
        assertEquals(1, errors.size());
        errors = fieldErrors.get("testBeanList[4].name");
        assertEquals(1, errors.size());
    }
