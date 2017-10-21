    public void testGetFromInitParameters() {
        mockPortletContext.stubs().method("getAttribute").with(eq("dummyKey"));
        mockPortletContext.stubs().method("getInitParameter").with(
                eq("dummyKey")).will(returnValue("dummyValue"));

        PortletApplicationMap map = new PortletApplicationMap(
                (PortletContext) mockPortletContext.proxy());

        assertEquals("dummyValue", map.get("dummyKey"));
    }
