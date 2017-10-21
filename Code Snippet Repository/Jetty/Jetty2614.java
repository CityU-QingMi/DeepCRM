    private void checkWildcardHost(boolean succeed, String[] ruleHosts, String[] requestHosts) throws Exception
    {
        _fooContainerRule.setVirtualHosts(ruleHosts);
        _handler.setRules(new Rule[] { _fooContainerRule });

        for(String host: requestHosts)
        {
            _request.setAuthority(host,0);
            _request.setURIPathQuery("/cheese/bar");
            handleRequest();
            if(succeed)
                assertEquals("{_fooContainerRule, Host: "+host+"}: should apply _fooRule", "/cheese/fooRule", _request.getRequestURI());
            else
                assertEquals("{_fooContainerRule, Host: "+host+"}: should not apply _fooRule", "/cheese/bar", _request.getRequestURI());
        }
    }
