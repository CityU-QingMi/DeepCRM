    public void testEntrySet() {
    	MockPortletRequest request = new MockPortletRequest();
    	request.setAttribute("testAttribute1", "testValue1");
    	request.setAttribute("testAttribute2", "testValue2");

        PortletRequestMap map = new PortletRequestMap(request);
        Set entries = map.entrySet();

        assertEquals(3, entries.size());
        Iterator it = entries.iterator();
        for (Iterator iterator = entries.iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            checkEntry(entry);
	}
    }
