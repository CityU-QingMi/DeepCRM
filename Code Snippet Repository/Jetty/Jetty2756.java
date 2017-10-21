    public CachedContentFactory(CachedContentFactory parent, ResourceFactory factory, MimeTypes mimeTypes,boolean useFileMappedBuffer,boolean etags,CompressedContentFormat[] precompressedFormats)
    {
        _factory = factory;
        _cache=new ConcurrentHashMap<String,CachedHttpContent>();
        _cachedSize=new AtomicInteger();
        _cachedFiles=new AtomicInteger();
        _mimeTypes=mimeTypes;
        _parent=parent;
        _useFileMappedBuffer=useFileMappedBuffer;
        _etags=etags;
        _precompressedFormats=precompressedFormats;
    }
