    @Override
    public void setHeader(String name, String value)
    {
        // remove from the real response
        if (response!=null)
            response.setHeader(name,null);

       
        List<String> values = headers.get(name);
        if (values==null)
        {
            values = new ArrayList<>();
            headers.put(name,values);
        }
        else
            values.clear();
        values.add(value);
    }
