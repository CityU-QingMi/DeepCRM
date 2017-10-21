    public ResourceHttpContent(final Resource resource, final String contentType, int maxBuffer, Map<CompressedContentFormat, HttpContent> precompressedContents)
    {
        _resource=resource;
        _contentType=contentType;
        _maxBuffer = maxBuffer;
        if (precompressedContents == null)
        {
            _precompressedContents = null;
        }
        else
        {
            _precompressedContents = new HashMap<>(precompressedContents.size());
            for (Map.Entry<CompressedContentFormat, HttpContent> entry : precompressedContents.entrySet())
            {
                _precompressedContents.put(entry.getKey(),new PrecompressedHttpContent(this,entry.getValue(),entry.getKey()));
            }
        }
    }
