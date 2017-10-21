    public void testRemove() {
    	MockPortletRequest request = new MockPortletRequest();
    	PortletSession session = request.getPortletSession();
    	session.setAttribute("testAttribute1", "testValue1");

        PortletSessionMap map = new PortletSessionMap(request);
        Object ret = map.remove("testAttribute1");
        // Assert that the element that was removed was returned and the key is no longer in the
        // portlet session
        assertEquals("testValue1", ret);
        assertNull(session.getAttribute("testAttribute1"));
    }
