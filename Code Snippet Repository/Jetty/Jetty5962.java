    public void setTempDirectory(File dir)
    {
        if (isStarted())
            throw new IllegalStateException("Started");

        if (dir!=null)
        {
            try{dir=new File(dir.getCanonicalPath());}
            catch (IOException e){LOG.warn(Log.EXCEPTION,e);}
        }

        _tmpDir=dir;
        setAttribute(TEMPDIR,_tmpDir);            
    }
