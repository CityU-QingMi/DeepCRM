    @Override
    public void addHeader(String name, String value)
    {
        String key = name;
        List<String> values = headers.get(key);
        if (values == null)
        {
            values = new ArrayList<>();
        }
        values.add(value);
        headers.put(key,values);
    }
