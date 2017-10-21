    public void complete()
    {
        if (response==null)
            return;

        // Take a copy of all the real response headers
        Map<String, Collection<String>> real = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (String name : response.getHeaderNames())
        {
            real.put(name,response.getHeaders(name));
        }
        
        // Transfer all headers to the real HTTP response
        for (Map.Entry<String, List<String>> entry : getHeaders().entrySet())
        {
            for (String value : entry.getValue())
            {
                response.addHeader(entry.getKey(), value);
            }
        }
        
        // Prepend the real headers to the copy headers
        for (Map.Entry<String, Collection<String>> entry : real.entrySet())
        {
            String name = entry.getKey();
            Collection<String> prepend = entry.getValue();
            List<String> values = headers.getOrDefault(name,new ArrayList<>());
            values.addAll(0,prepend);
            headers.put(name, values);
        }
        
        status = response.getStatus();
        response = null;
    }
