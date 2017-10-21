    public void testCreateParametersForContext() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        MockHttpServletResponse res = new MockHttpServletResponse();
        Mock mockValueStack = new Mock(ValueStack.class);
        HashMap ctx = new HashMap();
        mockValueStack.expectAndReturn("getContext", ctx);
        mockValueStack.expectAndReturn("getContext", ctx);
        mockValueStack.expectAndReturn("getContext", ctx);
        
        ActionComponent comp = new ActionComponent((ValueStack) mockValueStack.proxy(), req, res);
        comp.addParameter("foo", "bar");
        comp.addParameter("baz", new String[]{"jim", "sarah"});
        HttpParameters params = comp.createParametersForContext();
        assertNotNull(params);
        assertEquals(2, params.keySet().size());
        assertEquals("bar", params.get("foo").getValue());
        assertEquals(2, params.get("baz").getMultipleValues().length);
        mockValueStack.verify();
    }
