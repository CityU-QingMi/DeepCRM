    private String urlEncode(String value)
    {
        if (value == null)
            return "";

        String encoding = "utf-8";
        try
        {
            return URLEncoder.encode(value, encoding);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new UnsupportedCharsetException(encoding);
        }
    }
