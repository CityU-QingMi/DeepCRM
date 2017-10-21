	public static void invokePrefixMethod(ActionInvocation actionInvocation, String[] prefixes) throws InvocationTargetException, IllegalAccessException {
		Object action = actionInvocation.getAction();
		
		String methodName = actionInvocation.getProxy().getMethod();
		
		if (methodName == null) {
			// if null returns (possible according to the docs), use the default execute 
	        methodName = DEFAULT_INVOCATION_METHODNAME;
		}
		
		Method method = getPrefixedMethod(prefixes, methodName, action);
		if (method != null) {
			method.invoke(action, new Object[0]);
		}
	}
