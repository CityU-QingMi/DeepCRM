    public void testClear() {

        mockPortletContext.expects(once()).method("removeAttribute").with(eq("key1"));
        mockPortletContext.expects(once()).method("removeAttribute").with(eq("key2"));

        ArrayList<String> dummy = new ArrayList<String>();
        dummy.add("key1");
        dummy.add("key2");

        mockPortletContext.expects(once()).method("getAttributeNames").will(
                returnValue(Collections.enumeration(dummy)));

        PortletApplicationMap map = new PortletApplicationMap(portletContext);
        map.clear();
    }
