    @Override
    public void applyURI(Request request, String oldURI, String newURI) throws IOException
    {
        if (_query == null)
        {
            request.setURIPathQuery(newURI);
        }
        else
        {
            String queryString = request.getQueryString();
            if (queryString != null)
                queryString = queryString + "&" + _query;
            else
                queryString = _query;
            request.setURIPathQuery(newURI);
            request.setQueryString(queryString);
        }
    }
