    @Override
    protected void doStart() throws Exception
    {
        __log.info("Node={} Scan=",_nodeName,_scanDir);
        if (_scanDir==null || !_scanDir.exists())
            throw new IllegalStateException("!scandir");

        _scanDirURI=_scanDir.toURI().getPath();
        _scanner.setScanDepth(6); // enough for templates/name/webapps/WEB-INF/lib/foo.jar
        List<File> dirs = Arrays.asList(new File[]
                                                 {
                new File(_scanDir,WEBAPPS),
                new File(_scanDir,TEMPLATES),
                new File(_scanDir,NODES),
                new File(_scanDir,INSTANCES)
            });
        for (File file : dirs)
        {
            if (!file.exists() && !file.isDirectory())
                __log.warn("No directory: "+file.getAbsolutePath());
        }
        _scanner.setScanDirs(dirs);
        _scanner.addListener(_listener);
        _scanner.start();
        
        super.doStart();
    }
