    @Override
    public String getCharacterEncoding()
    {
        if (_characterEncoding == null)
        {
            String encoding = MimeTypes.getCharsetAssumedFromContentType(_contentType);
            if (encoding!=null)
                return encoding;
            encoding = MimeTypes.getCharsetInferredFromContentType(_contentType);
            if (encoding!=null)
                return encoding;
            return StringUtil.__ISO_8859_1;
        }
        return _characterEncoding;
    }
