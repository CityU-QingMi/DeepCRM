    public void testEntrySet() {
    	MockPortletRequest request = new MockPortletRequest();
    	PortletSession session = request.getPortletSession();
    	session.setAttribute("testAttribute1", "testValue1");
    	session.setAttribute("testAttribute2", "testValue2");

        PortletSessionMap map = new PortletSessionMap(request);
        Set entries = map.entrySet();

        assertEquals(2, entries.size());
        Iterator it = entries.iterator();
        Map.Entry entry = (Map.Entry)it.next();
        checkEntry(entry);
        entry = (Map.Entry)it.next();
        checkEntry(entry);

    }
