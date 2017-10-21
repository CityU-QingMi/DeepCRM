    public boolean hasFiles(BaseHome baseHome, Props props)
    {
        for (String ref : getFiles())
        {
            FileArg farg = new FileArg(this,props.expand(ref));
            Path refPath = baseHome.getBasePath(farg.location);
            if (!Files.exists(refPath))
            {
                return false;
            }
        }
        return true;
    }
