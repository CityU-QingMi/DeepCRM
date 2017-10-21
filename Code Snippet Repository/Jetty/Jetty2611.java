    @Test
    public void testEmptyVirtualHost() throws Exception
    {
        _request.setAuthority("cheese.com",0);

        _handler.setRules(new Rule[] { _fooContainerRule });
        _fooContainerRule.setVirtualHosts(null);
        handleRequest();
        assertEquals("{_fooContainerRule: virtual hosts array is null, Host: cheese.com}: apply _fooRule", "/cheese/fooRule", _request.getRequestURI());

        _request.setURIPathQuery("/cheese/bar");
        _request.setURIPathQuery("/cheese/bar");
        _fooContainerRule.setVirtualHosts(new String[] {});
        handleRequest();
        assertEquals("{_fooContainerRule: virtual hosts array is empty, Host: cheese.com}: apply _fooRule", "/cheese/fooRule", _request.getRequestURI());

        _request.setURIPathQuery("/cheese/bar");
        _request.setURIPathQuery("/cheese/bar");
        _fooContainerRule.setVirtualHosts(new String[] {null});
        handleRequest();
        assertEquals("{_fooContainerRule: virtual host is null, Host: cheese.com}: apply _fooRule", "/cheese/fooRule", _request.getRequestURI());

    }
