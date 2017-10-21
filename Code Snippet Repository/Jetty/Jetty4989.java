    @Override
    public String nextToken(String delim)
        throws NoSuchElementException
    {
        _delim=delim;
        _i=_lastStart;
        _token.setLength(0);
        _hasToken=false;
        return nextToken();
    }
