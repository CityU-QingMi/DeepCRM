    public void testParametersDoesNotAffectSession() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("blah", "This is blah");
        params.put("#session.foo", "Foo");
        params.put("\u0023session[\'user\']", "0wn3d");
        params.put("\\u0023session[\'user\']", "0wn3d");
        params.put("\u0023session.user2", "0wn3d");
        params.put("\\u0023session.user2", "0wn3d");
        params.put("('\u0023'%20%2b%20'session[\'user3\']')(unused)", "0wn3d");
        params.put("('\\u0023' + 'session[\\'user4\\']')(unused)", "0wn3d");
        params.put("('\u0023'%2b'session[\'user5\']')(unused)", "0wn3d");
        params.put("('\\u0023'%2b'session[\'user5\']')(unused)", "0wn3d");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.PARAM_INTERCEPTOR_ACTION_NAME, null, extraContext);
        ValueStack stack = proxy.getInvocation().getStack();
        HashMap<String, Object> session = new HashMap<>();
        stack.getContext().put("session", session);
        proxy.execute();
        assertEquals("This is blah", ((SimpleAction) proxy.getAction()).getBlah());
        assertNull(session.get("foo"));
        assertNull(session.get("user"));
        assertNull(session.get("user2"));
        assertNull(session.get("user3"));
        assertNull(session.get("user4"));
        assertNull(session.get("user5"));
    }
