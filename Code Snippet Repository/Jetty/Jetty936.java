    @Override
    public Headers getRequestHeaders()
    {
        Headers headers = new Headers();
        Enumeration<?> en = _req.getHeaderNames();
        while (en.hasMoreElements())
        {
            String name = (String)en.nextElement();
            Enumeration<?> en2 = _req.getHeaders(name);
            while (en2.hasMoreElements())
            {
                String value = (String)en2.nextElement();
                headers.add(name,value);
            }
        }
        return headers;
    }
