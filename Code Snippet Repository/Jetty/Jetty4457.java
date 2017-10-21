    private boolean processFileResource(FileArg arg) throws IOException
    {
        URI uri = arg.uri==null?null:URI.create(arg.uri);

        if (startArgs.isCreateFiles())
        {
            for (FileInitializer finit : fileInitializers)
            {
                if (finit.isApplicable(uri))
                    return finit.create(uri,arg.location);
            }
            
            throw new IOException(String.format("Unable to create %s",arg));
        }

        for (FileInitializer finit : fileInitializers)
        {
            if (finit.isApplicable(uri))
                if (!finit.check(uri,arg.location))
                    startArgs.setRun(false);
        }
        return false;
    }
