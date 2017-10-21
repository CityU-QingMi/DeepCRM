    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof CompressedContentFormat))
            return false;
        CompressedContentFormat ccf = (CompressedContentFormat)o;
        if (_encoding==null && ccf._encoding!=null)
            return false;
        if (_extension==null && ccf._extension!=null)
            return false;
        
        return  _encoding.equalsIgnoreCase(ccf._encoding) && _extension.equalsIgnoreCase(ccf._extension);
    }
