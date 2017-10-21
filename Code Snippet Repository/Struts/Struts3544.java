    public void testClear() {
        MockPortletRequest req = new MockPortletRequest();
        PortletSession session = req.getPortletSession();
    	session.setAttribute("testAttribute1", "testValue1");
    	session.setAttribute("testAttribute2", "testValue2");
        
        PortletSessionMap map = new PortletSessionMap(req);
        map.clear();
        
        // Assert that there are no elements in the portlet session
        assertFalse(req.getPortletSession().getAttributeNames().hasMoreElements());
    }
