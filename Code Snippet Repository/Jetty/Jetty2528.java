    @Override
    public String apply(String target, HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        // process header
        if (_add)
            response.addHeader(_name, _value);
        else
            response.setHeader(_name, _value); 
        return target;
    }
