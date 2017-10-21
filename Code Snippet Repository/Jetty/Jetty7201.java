    @Override
    public List<String> getHeaders(String name)
    {
        if (response==null)
            return headers.get(name);
        
        List<String> values = new ArrayList<>(response.getHeaders(name));
        values.addAll(headers.get(name));
        return values.isEmpty()?null:values;
    }
