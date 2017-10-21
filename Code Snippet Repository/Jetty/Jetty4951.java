    public MultiPartInputStreamParser (InputStream in, String contentType, MultipartConfigElement config, File contextTmpDir)
    {
        _contentType = contentType;
        _config = config;
        _contextTmpDir = contextTmpDir;
        if (_contextTmpDir == null)
            _contextTmpDir = new File (System.getProperty("java.io.tmpdir"));

        if (_config == null)
            _config = new MultipartConfigElement(_contextTmpDir.getAbsolutePath());
        
        if (in instanceof ServletInputStream)
        {
            if (((ServletInputStream)in).isFinished())
            {
                _parts = EMPTY_MAP;
                return;
            }
        }
        _in = new ReadLineInputStream(in);
    }
