    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(name);
        for (Parameter param : parameters)
        {
            str.append(';');
            str.append(param.getName());
            String value = param.getValue();
            if (value != null)
            {
                str.append('=');
                QuoteUtil.quoteIfNeeded(str,value,";=");
            }
        }
        return str.toString();
    }
