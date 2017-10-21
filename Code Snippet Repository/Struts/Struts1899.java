    public void testTwoMultiselect() throws Exception {
        param.put("user", "batman");
        param.put("email", "batman@comic.org");
        param.put("__multiselect_superpower", "");
        param.put("superpower", "yes");
        param.put("__multiselect_cool", "");
        assertTrue(param.containsKey("__multiselect_superpower"));
        assertTrue(param.containsKey("__multiselect_cool"));

        ai.getInvocationContext().setParameters(HttpParameters.create(param).build());

        interceptor.init();
        interceptor.intercept(ai);
        interceptor.destroy();

        HttpParameters parameters = ai.getInvocationContext().getParameters();
        assertFalse(parameters.contains("__multiselect_superpower"));
        assertFalse(parameters.contains("__multiselect_cool"));
        assertEquals(4, parameters.keySet().size()); // should be 4 as __multiselect_ should be removed
        assertEquals("yes", parameters.get("superpower").getValue());
        assertFalse(parameters.get("cool").isDefined());
    }
