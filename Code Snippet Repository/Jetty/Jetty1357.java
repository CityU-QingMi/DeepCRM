    public PrecompressedHttpContent(HttpContent content, HttpContent precompressedContent, CompressedContentFormat format)
    {
        _content = content;
        _precompressedContent = precompressedContent;
        _format = format;
        if (_precompressedContent == null || _format == null)
        {
            throw new NullPointerException("Missing compressed content and/or format");
        }
    }
