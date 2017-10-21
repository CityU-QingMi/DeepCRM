    public void testClassPollutionBlockedByOgnl() throws Exception {
        // given
        final String pollution1 = "class.classLoader.jarPath";
        final String pollution2 = "model.class.classLoader.jarPath";
        final String pollution3 = "class.classLoader.defaultAssertionStatus";

        loadConfigurationProviders(new XWorkConfigurationProvider(), new XmlConfigurationProvider("xwork-class-param-test.xml"));
        final Map<String, Object> params = new HashMap<String, Object>() {
            {
                put(pollution1, "bad");
                put(pollution2, "very bad");
                put(pollution3, true);
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
        assertEquals(3, action.getActionMessages().size());

        String msg1 = action.getActionMessage(0);
        String msg2 = action.getActionMessage(1);
        String msg3 = action.getActionMessage(2);

        assertEquals("Error setting expression 'class.classLoader.defaultAssertionStatus' with value 'true'", msg1);
        assertEquals("Error setting expression 'class.classLoader.jarPath' with value 'bad'", msg2);
        assertEquals("Error setting expression 'model.class.classLoader.jarPath' with value 'very bad'", msg3);

        assertFalse(excluded.get(pollution1));
        assertFalse(excluded.get(pollution2));
        assertFalse(excluded.get(pollution3));
    }
