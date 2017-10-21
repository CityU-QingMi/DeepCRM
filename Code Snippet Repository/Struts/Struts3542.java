    public void testPut() {

    	MockPortletRequest request = new MockPortletRequest();

        PortletSessionMap map = new PortletSessionMap(request);
        assertEquals("testValue1", map.put("testAttribute1", "testValue1"));
        assertEquals("testValue2", map.put("testAttribute2", "testValue2"));

        PortletSession session = request.getPortletSession();
        // Assert that the values has been propagated to the session
        assertEquals("testValue1", session.getAttribute("testAttribute1"));
        assertEquals("testValue2", session.getAttribute("testAttribute2"));
    }
