    public void testCreateRenderUrlWithNoModeOrState() throws Exception {
    	EasyMock.expect(renderResponse.createRenderURL()).andReturn(url);

        EasyMock.replay(renderRequest);
        EasyMock.replay(renderResponse);

        (new PortletUrlHelper()).buildUrl("testAction", null, null,
                new HashMap<String, Object>(), null, null, null);
        assertEquals(PortletMode.VIEW, url.getPortletMode());
        assertEquals(WindowState.NORMAL, url.getWindowState());
        assertEquals("testAction", url.getParameterMap().get(ACTION_PARAM)[0]);
        assertEquals("view", url.getParameterMap().get(MODE_PARAM)[0]);
    }
