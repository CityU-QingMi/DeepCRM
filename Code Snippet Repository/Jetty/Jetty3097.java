    @Deprecated
    public boolean isGzip()
    {
        for (CompressedContentFormat formats : _resourceService.getPrecompressedFormats())
        {
            if (CompressedContentFormat.GZIP._encoding.equals(formats._encoding))
                return true;
        }
        return false;
    }
