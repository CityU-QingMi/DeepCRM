    public void testExcludedParametersAreIgnored() throws Exception {
        ParametersInterceptor pi = createParametersInterceptor();
        pi.setExcludeParams("dojo\\..*");
        final Map<String, Object> actual = injectValueStackFactory(pi);
        ValueStack stack = injectValueStack(actual);

        final Map<String, Object> expected = new HashMap<String, Object>() {
            {
                put("fooKey", "fooValue");
            }
        };

        Map<String, Object> parameters = new HashMap<String, Object>() {
            {
                put("dojo.test", "dojoValue");
                put("fooKey", "fooValue");
            }
        };
        pi.setParameters(new NoParametersAction(), stack, HttpParameters.create(parameters).build());
        assertEquals(expected, actual);
    }
