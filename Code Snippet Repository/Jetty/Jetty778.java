    @Override
    protected void doStart() throws Exception
    {
        if (LOG.isDebugEnabled()) 
            LOG.debug(this.getClass().getSimpleName() + ".doStart()");
        if (_monitored.size()==0)
            throw new IllegalStateException("No configuration dir specified");

        LOG.info("Deployment monitor " + _monitored + " at interval " + _scanInterval);
        List<File> files = new ArrayList<>();
        for (Resource resource:_monitored)
        {
            if (resource.exists() && resource.getFile().canRead())
                files.add(resource.getFile());
            else
                LOG.warn("Does not exist: "+resource);
        }
        
        _scanner = new Scanner();
        _scanner.setScanDirs(files);
        _scanner.setScanInterval(_scanInterval);
        _scanner.setRecursive(_recursive);
        _scanner.setFilenameFilter(_filenameFilter);
        _scanner.setReportDirs(true);
        _scanner.addListener(_scannerListener);
        _scanner.start();
    }
