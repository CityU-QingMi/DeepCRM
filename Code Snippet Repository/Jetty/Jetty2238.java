    protected File tmpdir(String name,String suffix) throws IOException
    {
        File dir=_tmpDir;
        if (dir==null || !dir.isDirectory() || !dir.canWrite())
        {
            dir=new File(_scanDir,"tmp");
            if (!dir.isDirectory() || !dir.canWrite())
                dir=null;
        }
        
        File tmp = File.createTempFile(name+"_","."+suffix,dir);
        tmp=tmp.getCanonicalFile();
        if (tmp.exists())
            IO.delete(tmp);
        tmp.mkdir();
        tmp.deleteOnExit();
        return tmp;
    }
