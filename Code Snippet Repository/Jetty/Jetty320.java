    private Request param(String name, String value, boolean fromQuery)
    {
        params.add(name, value);
        if (!fromQuery)
        {
            // If we have an existing query string, preserve it and append the new parameter.
            if (query != null)
                query += "&" + urlEncode(name) + "=" + urlEncode(value);
            else
                query = buildQuery();
            uri = null;
        }
        return this;
    }
