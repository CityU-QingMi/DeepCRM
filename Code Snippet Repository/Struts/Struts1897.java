    public void testSelectedMultiselect() throws Exception {
        param.put("user", "batman");
        param.put("email", "batman@comic.org");
        param.put("superpower", "robin");
        param.put("__multiselect_superpower", "");
        assertTrue(param.containsKey("__multiselect_superpower"));

        ai.getInvocationContext().setParameters(HttpParameters.create(param).build());

        interceptor.init();
        interceptor.intercept(ai);
        interceptor.destroy();

        HttpParameters parameters = ai.getInvocationContext().getParameters();
        assertFalse(parameters.contains("__multiselect_superpower"));
        assertEquals(3, parameters.keySet().size()); // should be 3 as __multiselect_ should be removed
        assertEquals("robin", parameters.get("superpower").getValue());
    }
