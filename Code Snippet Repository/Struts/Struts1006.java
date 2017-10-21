    public void testCommandInvocationUnknownHandler() throws Exception {

        UnknownHandler unknownHandler = new UnknownHandler() {
			public ActionConfig handleUnknownAction(String namespace, String actionName) throws XWorkException {
                return new ActionConfig.Builder("test", actionName, ActionSupport.class.getName())
                        .addAllowedMethod("unknownmethod")
                        .build();
            }
			public Result handleUnknownResult(ActionContext actionContext, String actionName, ActionConfig actionConfig, String resultCode) throws XWorkException {
				return new MockResult();
			}
			public Object handleUnknownActionMethod(Object action, String methodName) {
				if (methodName.equals("unknownmethod")) {
					return "found";
				} else {
					return null;
				}
			}
        };

        UnknownHandlerManagerMock uhm = new UnknownHandlerManagerMock();
        uhm.addUnknownHandler(unknownHandler);

        loadButAdd(UnknownHandlerManager.class, uhm);

        DefaultActionProxy baseActionProxy = (DefaultActionProxy) actionProxyFactory.createActionProxy(
                "baz", "unknownMethodTest", "unknownmethod", null);

        ((DefaultActionInvocation)baseActionProxy.getInvocation()).setUnknownHandlerManager(uhm);

        assertEquals("found", baseActionProxy.execute());
    }
