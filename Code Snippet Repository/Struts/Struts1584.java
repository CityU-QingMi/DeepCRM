    public void testMessageKey() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("foo", "200");

        HashMap<String, Object> extraContext = new HashMap<>();
        extraContext.put(ActionContext.PARAMETERS, HttpParameters.create(params).build());

        try {
            ActionProxy proxy = actionProxyFactory.createActionProxy("", MockConfigurationProvider.VALIDATION_ACTION_NAME, null, extraContext);
            ValueStack stack = ActionContext.getContext().getValueStack();
            ActionContext.setContext(new ActionContext(stack.getContext()));
            ActionContext.getContext().setLocale(Locale.US);
            proxy.execute();
            assertTrue(((ValidationAware) proxy.getAction()).hasFieldErrors());

            Map<String, List<String>> errors = ((ValidationAware) proxy.getAction()).getFieldErrors();
            List<String> fooErrors = errors.get("foo");
            assertEquals(1, fooErrors.size());

            String errorMessage = fooErrors.get(0);
            assertNotNull(errorMessage);
            assertEquals("Foo Range Message", errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
