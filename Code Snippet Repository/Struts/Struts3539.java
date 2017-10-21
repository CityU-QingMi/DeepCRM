    public void testClear() {
    	MockPortletRequest request = new MockPortletRequest();
    	request.setAttribute("testAttribute1", "testValue1");
    	request.setAttribute("testAttribute2", "testValue2");


        PortletRequestMap map = new PortletRequestMap(request);
        map.clear();

        assertFalse(request.getAttributeNames().hasMoreElements());
    }
