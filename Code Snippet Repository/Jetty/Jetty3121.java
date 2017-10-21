    public GzipHandler()
    {
        _methods.include(HttpMethod.GET.asString());
        for (String type:MimeTypes.getKnownMimeTypes())
        {
            if ("image/svg+xml".equals(type))
                _paths.exclude("*.svgz");
            else if (type.startsWith("image/")||
                type.startsWith("audio/")||
                type.startsWith("video/"))
                _mimeTypes.exclude(type);
        }
        _mimeTypes.exclude("application/compress");
        _mimeTypes.exclude("application/zip");
        _mimeTypes.exclude("application/gzip");
        _mimeTypes.exclude("application/bzip2");
        _mimeTypes.exclude("application/brotli");
        _mimeTypes.exclude("application/x-xz");
        _mimeTypes.exclude("application/x-rar-compressed");

        if (LOG.isDebugEnabled())
            LOG.debug("{} mime types {}",this,_mimeTypes);
        
        _agentPatterns.exclude(".*MSIE 6.0.*");
    }
