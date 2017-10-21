	public void execute(ActionInvocation invocation) throws Exception {
		actionName = conditionalParse(actionName, invocation);
		String portletNamespace = (String)invocation.getInvocationContext().get(PortletConstants.PORTLET_NAMESPACE);
		if (portletMode != null) {
			Map<PortletMode, String> namespaceMap = getNamespaceMap(invocation);
			namespace = namespaceMap.get(portletMode);
		}
		if (namespace == null) {
			namespace = invocation.getProxy().getNamespace();
		} else {
			namespace = conditionalParse(namespace, invocation);
		}
		if (method == null) {
			method = "";
		} else {
			method = conditionalParse(method, invocation);
		}

		String resultCode = invocation.getResultCode();
		if (resultCode != null) {
			ResultConfig resultConfig = invocation.getProxy().getConfig().getResults().get(resultCode);
			Map<String, String> resultConfigParams = resultConfig.getParams();
            for (Map.Entry<String, String> e : resultConfigParams.entrySet()) {
                if (!prohibitedResultParam.contains(e.getKey())) {
                    requestParameters.put(e.getKey(), e.getValue() == null ? "" : conditionalParse(e.getValue(), invocation));
                }
            }
		}

		StringBuilder tmpLocation = new StringBuilder(actionMapper.getUriFromActionMapping(new ActionMapping(actionName,
				(portletNamespace == null ? namespace : portletNamespace + namespace), method, null)));
		urlHelper.buildParametersString(requestParameters, tmpLocation, "&");

		setLocation(tmpLocation.toString());

		super.execute(invocation);
	}
