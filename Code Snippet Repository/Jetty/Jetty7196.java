    @Override
    public void addHeader(String name, String value)
    {
        if (value!=null)
        {
            List<String> values = headers.get(name);
            if (values==null)
            {
                values = new ArrayList<>();
                headers.put(name,values);
            }
            values.add(value);
        }
    }
