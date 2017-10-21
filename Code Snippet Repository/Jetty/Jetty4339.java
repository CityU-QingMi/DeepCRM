    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        mimeTypes = new MimeTypes();
        // Some real world, yet not terribly common, mime type mappings.
        mimeTypes.addMimeMapping("bz2","application/bzip2");
        mimeTypes.addMimeMapping("br","application/brotli");
        mimeTypes.addMimeMapping("bmp","image/bmp");
        mimeTypes.addMimeMapping("tga","application/tga");
        mimeTypes.addMimeMapping("xcf","image/xcf");
        mimeTypes.addMimeMapping("jp2","image/jpeg2000");

        // Some of the other gzip mime-types seen in the wild.
        // NOTE: Using oddball extensions just so that the calling request can specify
        //       which strange mime type to use.
        mimeTypes.addMimeMapping("x-gzip","application/x-gzip");
        mimeTypes.addMimeMapping("x-gunzip","application/x-gunzip");
        mimeTypes.addMimeMapping("gzipped","application/gzippped");
        mimeTypes.addMimeMapping("gzip-compressed","application/gzip-compressed");
        mimeTypes.addMimeMapping("x-compressed","application/x-compressed");
        mimeTypes.addMimeMapping("x-compress","application/x-compress");
        mimeTypes.addMimeMapping("gzipdoc","gzip/document");
    }
