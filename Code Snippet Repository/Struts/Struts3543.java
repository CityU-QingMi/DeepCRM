    public void testGet() {
    	MockPortletRequest request = new MockPortletRequest();
    	PortletSession session = request.getPortletSession();
    	session.setAttribute("testAttribute1", "testValue1");
    	session.setAttribute("testAttribute2", "testValue2");
        PortletSessionMap map = new PortletSessionMap(request);
        Object val1 = map.get("testAttribute1");
        Object val2 = map.get("testAttribute2");
        // Assert that the values from the session is in the map
        assertEquals("testValue1", val1);
        assertEquals("testValue2", val2);
    }
