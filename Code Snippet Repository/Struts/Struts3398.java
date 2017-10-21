    protected ActionProxy getActionProxy(String httpMethod, String uri) {
		request.setRequestURI(uri);
		request.setMethod(httpMethod);

		ActionMapping mapping = getActionMapping(request);
		String namespace = mapping.getNamespace();
		String name = mapping.getName();
		String method = mapping.getMethod();

		Configuration config = configurationManager.getConfiguration();
		ActionProxy proxy = config.getContainer()
				                    .getInstance(ActionProxyFactory.class)
				                    .createActionProxy(namespace, name, method, new HashMap<String, Object>(), true, false);

        ActionContext invocationContext = proxy.getInvocation().getInvocationContext();
        invocationContext.getContextMap().put(ServletActionContext.ACTION_MAPPING, mapping);
        invocationContext.setParameters(HttpParameters.create(request.getParameterMap()).build());
        // set the action context to the one used by the proxy
        ActionContext.setContext(invocationContext);

        // set the action context to the one used by the proxy
        ActionContext.setContext(invocationContext);

        // this is normally done in onSetUp(), but we are using Struts internal
        // objects (proxy and action invocation)
        // so we have to hack around so it works
		ServletActionContext.setServletContext(servletContext);
		ServletActionContext.setRequest(request);
		ServletActionContext.setResponse(response);

		return proxy;
	}
