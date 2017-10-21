    public void testClassPollutionBlockedByPattern() throws Exception {
        // given
        final String pollution1 = "class.classLoader.jarPath";
        final String pollution2 = "model.class.classLoader.jarPath";

        loadConfigurationProviders(new XWorkConfigurationProvider(), new XmlConfigurationProvider("xwork-param-test.xml"));
        final Map<String, Object> params = new HashMap<String, Object>() {
            {
                put(pollution1, "bad");
                put(pollution2, "very bad");
            }
        };

        final Map<String, Boolean> excluded = new HashMap<>();
        ParametersInterceptor pi = new ParametersInterceptor() {

            @Override
            protected boolean isExcluded(String paramName) {
                boolean result = super.isExcluded(paramName);
                excluded.put(paramName, result);
                return result;
            }

        };

        container.inject(pi);
        ValueStack vs = ActionContext.getContext().getValueStack();

        // when
        ValidateAction action = new ValidateAction();
        pi.setParameters(action, vs, HttpParameters.create(params).build());

        // then
        assertEquals(0, action.getActionMessages().size());
        assertTrue(excluded.get(pollution1));
        assertTrue(excluded.get(pollution2));
    }
