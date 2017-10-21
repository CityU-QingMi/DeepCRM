    public void testCreateRenderUrlWithDifferentPortletMode() throws Exception {
    	EasyMock.expect(renderResponse.createRenderURL()).andReturn(url);

        EasyMock.replay(renderRequest);
        EasyMock.replay(renderResponse);

        (new PortletUrlHelper()).buildUrl("testAction", null, null,
                new HashMap<String, Object>(), null, "edit", null);
        
        assertEquals(PortletMode.EDIT, url.getPortletMode());
        assertEquals(WindowState.NORMAL, url.getWindowState());
        assertEquals("testAction", url.getParameterMap().get(ACTION_PARAM)[0]);
        assertEquals("edit", url.getParameterMap().get(MODE_PARAM)[0]);
    }
