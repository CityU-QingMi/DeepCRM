    public void testAnnotatedMethodFailure() throws Exception {

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create().build());

        ActionProxy proxy = actionProxyFactory.createActionProxy("", "annotatedMethod", null, extraContext);
        proxy.execute();
        assertTrue(((ValidationAware) proxy.getAction()).hasActionErrors());
        Collection errors = ((ValidationAware) proxy.getAction()).getActionErrors();
        assertEquals(1, errors.size());

        assertEquals("Need param1 or param2.", errors.iterator().next());

    }
