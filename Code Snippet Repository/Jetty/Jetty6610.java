    @Override
    public void setConfig(ExtensionConfig config)
    {
        super.setConfig(config);
        StringBuilder s = new StringBuilder();
        s.append(config.getName());
        s.append("@").append(Integer.toHexString(hashCode()));
        s.append("[");
        boolean delim = false;
        for (String param : config.getParameterKeys())
        {
            if (delim)
            {
                s.append(';');
            }
            s.append(param).append('=').append(QuotedStringTokenizer.quoteIfNeeded(config.getParameter(param,""),";="));
            delim = true;
        }
        s.append("]");
        id = s.toString();
    }
