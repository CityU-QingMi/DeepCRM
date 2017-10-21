    @Override
    public void setHeaders(Map<String, List<String>> headers)
    {
        clearHeaders();

        for (Map.Entry<String, List<String>> entry : headers.entrySet())
        {
            String name = entry.getKey();
            List<String> values = entry.getValue();
            setHeader(name,values);
        }
    }
