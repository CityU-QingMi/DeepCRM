    @Test
    public void testRequestUriEnabled() throws IOException
    {
        for (String[] test : _tests)
        {
            reset();
            _request.setURIPathQuery(null);
            
            String t=test[0]+"?"+test[1]+">"+test[2]+"|"+test[3];
            _rule.setRegex(test[2]);
            _rule.setReplacement(test[3]);

            _request.setURIPathQuery(test[0]+(test[1]==null?"":("?"+test[1])));
            
            String result = _rule.matchAndApply(test[0], _request, _response);
            assertEquals(t, test[4], result);
            _rule.applyURI(_request,test[0],result);

            if (result!=null)
            {
                assertEquals(t,test[4], _request.getRequestURI());
                assertEquals(t,test[5], _request.getQueryString());
            }
            
            if (test[5]!=null)
            {
                MultiMap<String> params=new MultiMap<String>();
                UrlEncoded.decodeTo(test[5],params, StandardCharsets.UTF_8);
                               
                for (String n:params.keySet())
                    assertEquals(params.getString(n),_request.getParameter(n));
            }
        }
    }
