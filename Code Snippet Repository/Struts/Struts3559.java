	public void testProcessAction_ok() {
		final Mock mockResponse = mock(ActionResponse.class);

		PortletMode mode = PortletMode.VIEW;
		Map<String, String> initParams = new HashMap<String, String>();
		initParams.put("viewNamespace", "/view");

		Map<String, String[]> requestParams = new HashMap<String, String[]>();
		requestParams.put(ACTION_PARAM, new String[] { "/view/testAction" });
		requestParams.put(MODE_PARAM, new String[] { mode.toString() });

		initParams.put(StrutsConstants.STRUTS_ALWAYS_SELECT_FULL_NAMESPACE,
				"true");
		initPortletConfig(initParams, new HashMap<String, Object>());
		initRequest(requestParams, new HashMap<String, Object>(),
				new HashMap<String, Object>(), PortletMode.VIEW,
				WindowState.NORMAL, true, null);
		setupActionFactory("/view", "testAction", "success",
				EasyMock.createNiceMock(ValueStack.class));

		try {
			dispatcher
					.setActionProxyFactory((ActionProxyFactory) mockActionFactory
							.proxy());
			dispatcher.init((PortletConfig) mockConfig.proxy());
			dispatcher.processAction((ActionRequest) mockRequest.proxy(),
					(ActionResponse) mockResponse.proxy());
		} catch (Exception e) {
			e.printStackTrace();
			fail("Error occured");
		}
	}
