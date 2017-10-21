    @Test
    public void testMultipleVirtualHosts() throws Exception
    {
        _request.setAuthority("foo.com",0);
        _handler.setRules(new Rule[] {_fooContainerRule });

        _fooContainerRule.setVirtualHosts(new String[]{ "cheese.com" });
        handleRequest();
        assertEquals("{_fooContainerRule: vhosts[cheese.com], Host: foo.com}: no effect", "/cheese/bar", _request.getRequestURI());

        _request.setURIPathQuery("/cheese/bar");
        _fooContainerRule.addVirtualHost( "foo.com" );
        handleRequest();
        assertEquals("{_fooContainerRule: vhosts[cheese.com, foo.com], Host: foo.com}: apply _fooRule", "/cheese/fooRule", _request.getRequestURI());
    }
