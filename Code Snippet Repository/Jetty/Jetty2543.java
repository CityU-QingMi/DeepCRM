    @Override
    public void applyURI(Request request, String oldURI, String newURI) throws IOException
    {
        if (_query==null)
        {
            request.setURIPathQuery(newURI);
        }
        else
        {
            String query=(String)request.getAttribute("org.eclipse.jetty.rewrite.handler.RewriteRegexRule.Q");
            
            if (!_queryGroup && request.getQueryString()!=null)
                query=request.getQueryString()+"&"+query;
            request.setURIPathQuery(newURI);
            request.setQueryString(query);
        }
    }
