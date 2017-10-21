    @Test
    public void testContainedRequestUriEnabled() throws IOException
    {
        RuleContainer container = new RuleContainer();
        container.setRewriteRequestURI(true);
        container.addRule(_rule);
        for (String[] test : _tests)
        {
            reset();
            String t=test[0]+"?"+test[1]+">"+test[2]+"|"+test[3];
            _rule.setRegex(test[2]);
            _rule.setReplacement(test[3]);

            _request.setURIPathQuery(test[0]);
            _request.setQueryString(test[1]);
            _request.getAttributes().clearAttributes();
            
            String result = container.apply(URIUtil.decodePath(test[0]),_request,_response);
            assertEquals(t,URIUtil.decodePath(test[4]==null?test[0]:test[4]), result);
            assertEquals(t,test[4]==null?test[0]:test[4], _request.getRequestURI());
            assertEquals(t,test[5], _request.getQueryString());
        }
    }
