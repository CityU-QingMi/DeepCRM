    @Override
    public String getContentAsString()
    {
        String encoding = this.encoding;
        if (encoding == null)
        {
            return new String(getContent(), StandardCharsets.UTF_8);
        }
        else
        {
            try
            {
                return new String(getContent(), encoding);
            }
            catch (UnsupportedEncodingException e)
            {
                throw new UnsupportedCharsetException(encoding);
            }
        }
    }
