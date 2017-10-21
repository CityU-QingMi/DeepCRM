    public void testShouldMatch() {
        ActionConfig matched = matcher.match("foo/class/method");

        assertNotNull("ActionConfig should be matched", matched);
        assertTrue("ActionConfig should have properties, had " +
                matched.getParams().size(), matched.getParams().size() == 2);
        assertTrue("ActionConfig should have interceptors",
                matched.getInterceptors().size() == 1);
        assertTrue("ActionConfig should have ex mappings",
                matched.getExceptionMappings().size() == 1);
        assertTrue("ActionConfig should have external refs",
                matched.getExceptionMappings().size() == 1);
        assertTrue("ActionConfig should have results",
                matched.getResults().size() == 1);
    }
