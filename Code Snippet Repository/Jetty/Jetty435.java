    private static String encode(String value, Charset charset)
    {
        try
        {
            return URLEncoder.encode(value, charset.name());
        }
        catch (UnsupportedEncodingException x)
        {
            throw new UnsupportedCharsetException(charset.name());
        }
    }
