    private void initRequest(Map<String, String[]> requestParams, Map<String, Object> requestAttributes, Map<String, Object> sessionParams, PortletMode mode, WindowState state, boolean isEvent, Locale locale) {
        mockRequest = isEvent ? mock(ActionRequest.class) : mock(RenderRequest.class);
        mockSession = mock(PortletSession.class);
        mockSession.stubs().method(ANYTHING);
        mockRequest.stubs().method(ANYTHING);
        setupStub(sessionParams, mockSession, "getAttribute");
        mockSession.stubs().method("getAttributeNames").will(returnValue(Collections.enumeration(sessionParams.keySet())));
        setupParamStub(requestParams, mockRequest, "getParameter");
        setupStub(requestAttributes, mockRequest, "getAttribute");
        mockRequest.stubs().method("getAttributeNames").will(returnValue(Collections.enumeration(requestAttributes.keySet())));
        mockRequest.stubs().method("getParameterMap").will(returnValue(requestParams));
        mockRequest.stubs().method("getParameterNames").will(returnValue(Collections.enumeration(requestParams.keySet())));
        mockRequest.stubs().method("getPortletSession").will(returnValue(mockSession.proxy()));
        if(locale != null) {
            mockRequest.stubs().method("getLocale").will(returnValue(locale));
        }
        else {
            mockRequest.stubs().method("getLocale").will(returnValue(Locale.getDefault()));
        }
        mockRequest.stubs().method("getPortletMode").will(returnValue(mode));
        mockRequest.stubs().method("getWindowState").will(returnValue(state));
    }
