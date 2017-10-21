    public void setReplacement(String replacement)
    {
        if (replacement==null)
        {
            _replacement=null;
            _query=null;
        }
        else
        {
            String[] split = replacement.split("\\?", 2);
            _replacement = split[0];
            _query = split.length == 2 ? split[1] : null;
        }
    }
