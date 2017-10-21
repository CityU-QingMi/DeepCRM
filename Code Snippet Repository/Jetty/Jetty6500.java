    @Override
    public int getHeaderInt(String name)
    {
        List<String> values = headers.get(name);
        // no value list
        if (values == null)
        {
            return -1;
        }
        int size = values.size();
        // empty value list
        if (size <= 0)
        {
            return -1;
        }
        // simple return
        if (size == 1)
        {
            return Integer.parseInt(values.get(0));
        }
        throw new NumberFormatException("Cannot convert multi-value header into int");
    }
