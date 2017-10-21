    @Override
    public int getHeaderInt(String name)
    {
        String val = request.getHeader(name);
        if (val == null)
        {
            return -1;
        }
        return Integer.parseInt(val);
    }
