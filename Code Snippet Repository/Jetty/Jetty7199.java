    @Override
    public String getHeader(String name)
    {
        if (response!=null)
        {
            String value = response.getHeader(name);
            if (value!=null)
                return value;
        }
        List<String> values = headers.get(name);
        if (values!=null && !values.isEmpty())
            return values.get(0);
        return null;
    }
