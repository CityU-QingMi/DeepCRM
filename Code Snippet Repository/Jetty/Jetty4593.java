    public void add(ConfigSource source) throws IOException
    {
        if (sources.contains(source))
        {
            // TODO: needs a better/more clear error message
            throw new UsageException(ERR_BAD_ARG,"Duplicate Configuration Source Reference: " + source);
        }
        sources.add(source);
        Collections.sort(sources,new WeightedConfigSourceComparator());

        // look for --include-jetty-dir entries
        for (RawArgs.Entry arg : source.getArgs())
        {
            if (arg.startsWith("--include-jetty-dir"))
            {
                String ref = getValue(arg.getLine());
                String dirName = getProps().expand(ref);
                Path dir = FS.toPath(dirName).normalize().toAbsolutePath();
                DirConfigSource dirsource = new DirConfigSource(ref,dir,sourceWeight.incrementAndGet(),true);
                add(dirsource);
            }
        }
    }
