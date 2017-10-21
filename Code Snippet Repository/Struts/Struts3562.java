	private void initPortletConfig(final Map<String, String> initParams,
			final Map<String, Object> attributes) {
		mockConfig = mock(PortletConfig.class);
		mockCtx = mock(PortletContext.class);
		mockConfig.stubs().method(ANYTHING);
		mockCtx.stubs().method(ANYTHING);
		setupStub(initParams, mockConfig, "getInitParameter");
		mockCtx.stubs()
				.method("getAttributeNames")
				.will(returnValue(Collections.enumeration(attributes.keySet())));
		setupStub(attributes, mockCtx, "getAttribute");
		mockConfig.stubs().method("getPortletContext")
				.will(returnValue(mockCtx.proxy()));
		mockCtx.stubs()
				.method("getInitParameterNames")
				.will(returnValue(Collections.enumeration(initParams.keySet())));
		setupStub(initParams, mockCtx, "getInitParameter");
		mockConfig
				.stubs()
				.method("getInitParameterNames")
				.will(returnValue(Collections.enumeration(initParams.keySet())));
		setupStub(initParams, mockConfig, "getInitParameter");
		mockConfig.stubs().method("getResourceBundle")
				.will(returnValue(new ListResourceBundle() {
					protected Object[][] getContents() {
						return new String[][] { { "javax.portlet.title",
								"MyTitle" } };
					}
				}));
	}
