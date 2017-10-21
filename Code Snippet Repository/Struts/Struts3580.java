    public void testCreateActionUrl() throws Exception {
    	EasyMock.expect(renderResponse.createActionURL()).andReturn(url);
        
        EasyMock.replay(renderResponse);
        EasyMock.replay(renderRequest);
        
        (new PortletUrlHelper()).buildUrl("testAction", null, null,
                new HashMap<String, Object>(), "action", null, null);
        
        assertEquals(PortletMode.VIEW, url.getPortletMode());
        assertEquals(WindowState.NORMAL, url.getWindowState());
        assertEquals("testAction", url.getParameterMap().get(ACTION_PARAM)[0]);
        assertEquals("view", url.getParameterMap().get(MODE_PARAM)[0]);
    }
