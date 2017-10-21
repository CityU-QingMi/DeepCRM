    public void testModeChangeUsingPortletWidgets() {
        final Mock mockResponse = mock(RenderResponse.class);
        mockResponse.stubs().method(ANYTHING);
        PortletMode mode = PortletMode.EDIT;

        Map<String, String[]> requestParams = new HashMap<String, String[]>();
        requestParams.put(ACTION_PARAM, new String[]{"/view/testAction"});
        requestParams.put(EVENT_ACTION, new String[]{"false"});
        requestParams.put(MODE_PARAM, new String[]{PortletMode.VIEW.toString()});

        Map<String, Object> sessionMap = new HashMap<String, Object>();

        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("viewNamespace", "/view");
        initParams.put("editNamespace", "/edit");

        initPortletConfig(initParams, new HashMap<String, Object>());
        initRequest(requestParams, new HashMap<String, Object>(), sessionMap, mode, WindowState.NORMAL, false, null);
        setupActionFactory("/edit", "default", "success", EasyMock.createNiceMock(ValueStack.class));

        mockInvocation.expects(once()).method("getStack").will(
                returnValue(null));
        //mockSession.expects(once()).method("setAttribute").with(new Constraint[]{eq(PortletActionConstants.LAST_MODE), eq(PortletMode.VIEW)});
        try {
            dispatcher
                    .setActionProxyFactory((ActionProxyFactory) mockActionFactory
                            .proxy());
            dispatcher.init((PortletConfig) mockConfig.proxy());
            dispatcher.render((RenderRequest) mockRequest.proxy(),
                    (RenderResponse) mockResponse.proxy());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Error occured");
        }
    }
