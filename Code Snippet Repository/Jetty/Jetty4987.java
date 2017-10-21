    public QuotedStringTokenizer(String str,
                                 String delim,
                                 boolean returnDelimiters,
                                 boolean returnQuotes)
    {
        super("");
        _string=str;
        if (delim!=null)
            _delim=delim;
        _returnDelimiters=returnDelimiters;
        _returnQuotes=returnQuotes;

        if (_delim.indexOf('\'')>=0 ||
            _delim.indexOf('"')>=0)
            throw new Error("Can't use quotes as delimiters: "+_delim);

        _token=new StringBuffer(_string.length()>1024?512:_string.length()/2);
    }
