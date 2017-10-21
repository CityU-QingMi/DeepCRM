    public void configureTempDirectory (File dir, WebAppContext context)
    {
        if (dir == null)
            throw new IllegalArgumentException("Null temp dir");

        //if dir exists and we don't want it persisted, delete it
        if (dir.exists() && !context.isPersistTempDirectory())
        {
            if (!IO.delete(dir))
                throw new IllegalStateException("Failed to delete temp dir "+dir);
        }

        //if it doesn't exist make it
        if (!dir.exists())
            dir.mkdirs();

        if (!context.isPersistTempDirectory())
            dir.deleteOnExit();

        //is it useable
        if (!dir.canWrite() || !dir.isDirectory())   
            throw new IllegalStateException("Temp dir "+dir+" not useable: writeable="+dir.canWrite()+", dir="+dir.isDirectory());
    }
