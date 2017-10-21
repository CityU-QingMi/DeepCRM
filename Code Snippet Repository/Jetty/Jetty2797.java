    public EncodingHttpWriter(HttpOutput out, String encoding)
    {
        super(out);
        try
        {
            _converter = new OutputStreamWriter(_bytes, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
    }
