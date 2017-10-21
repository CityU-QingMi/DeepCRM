    public String toString()
    {
        StringBuffer strbuff = new StringBuffer();
        if (_resource != null)
            strbuff.append(_resource);
        if (_config != null)
        {
            strbuff.append(" [");
            strbuff.append(_config);
            strbuff.append("]");
        }
        return strbuff.toString();
    }
