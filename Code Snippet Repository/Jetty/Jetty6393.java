    public final String getParameterizedName()
    {
        StringBuilder str = new StringBuilder();
        str.append(name);
        for (String param : parameters.keySet())
        {
            str.append(';');
            str.append(param);
            String value = parameters.get(param);
            if (value != null)
            {
                str.append('=');
                QuoteUtil.quoteIfNeeded(str,value,";=");
            }
        }
        return str.toString();
    }
