    @Test
    public void testCaseInsensitiveHostname() throws Exception
    {
        _request.setAuthority("Foo.com",0);
        _fooContainerRule.setVirtualHosts(new String[] {"foo.com"} );

        _handler.setRules(new Rule[]{ _fooContainerRule });
        handleRequest();
        assertEquals("Foo.com and foo.com are equivalent", "/cheese/fooRule", _request.getRequestURI());
    }
