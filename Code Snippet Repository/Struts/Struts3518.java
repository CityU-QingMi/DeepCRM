	protected void executeActionResult(String finalLocation, ActionInvocation invocation) throws Exception {
        String location = finalLocation;
        String namespace = invocation.getProxy().getNamespace();
		if (LOG.isDebugEnabled()) {
			LOG.debug("Executing result in {} phase", (PortletActionContext.getPhase().isEvent()) ? "Event" : "Action");
			LOG.debug("Setting event render parameter location : {}", location);
			LOG.debug("Setting event render parameter namespace: {}", namespace);
		}
		Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();
		if (location.indexOf('?') != -1) {
			convertQueryParamsToRenderParams(location.substring(location.indexOf('?') + 1));
            location = location.substring(0, location.indexOf('?'));
		}
        PortletResponse response = PortletActionContext.getResponse();
		if (location.endsWith(".action")) {
			// View is rendered with a view action...luckily...
            location = location.substring(0, location.lastIndexOf("."));
			resultHelper.setRenderParameter(response, PortletConstants.ACTION_PARAM, location);
		} else {
			// View is rendered outside an action...uh oh...
			resultHelper.setRenderParameter(response, PortletConstants.ACTION_PARAM, "renderDirect");
			sessionMap.put(PortletConstants.RENDER_DIRECT_LOCATION, location);
		}
		resultHelper.setRenderParameter(response, PortletConstants.RENDER_DIRECT_NAMESPACE, namespace);
		if(portletMode != null) {
			resultHelper.setPortletMode(response, portletMode);
			resultHelper.setRenderParameter(response, PortletConstants.MODE_PARAM, portletMode.toString());
		}
		else {
			resultHelper.setRenderParameter(response, PortletConstants.MODE_PARAM, PortletActionContext.getRequest().getPortletMode()
					.toString());
		}
	}
