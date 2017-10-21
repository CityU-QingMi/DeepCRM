    public void testInternalParametersAreIgnored() throws Exception {
        // given
        ParametersInterceptor interceptor = createParametersInterceptor();
        final Map<String, Object> actual = injectValueStackFactory(interceptor);
        ValueStack stack = injectValueStack(actual);


        final Map<String, Object> expected = new HashMap<String, Object>() {
            {
                put("ordinary.bean", "value");
            }
        };

        Map<String, Object> parameters = new HashMap<String, Object>() {
            {
                put("ordinary.bean", "value");
                put("#some.internal.object", "true");
                put("(bla)#some.internal.object", "true");
                put("#some.internal.object(bla)#some.internal.object", "true");
                put("#_some.internal.object", "true");
                put("\u0023_some.internal.object", "true");
                put("\u0023_some.internal.object,[dfd],bla(\u0023_some.internal.object)", "true");
                put("\\u0023_some.internal.object", "true");
            }
        };

        // when
        interceptor.setParameters(new NoParametersAction(), stack, HttpParameters.create(parameters).build());

        // then
        assertEquals(expected, actual);
    }
