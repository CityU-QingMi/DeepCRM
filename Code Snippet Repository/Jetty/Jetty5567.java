    public static void main(String[] args)
    {
        List<Path> paths = new ArrayList<>();
        for (String arg : args)
        {
            paths.add(new File(arg).toPath());
        }

        if (paths.isEmpty())
        {
            LOG.warn("No paths specified on command line");
            System.exit(-1);
        }

        PathWatcherDemo demo = new PathWatcherDemo();
        try
        {
            demo.run(paths);
        }
        catch (Throwable t)
        {
            LOG.warn(t);
        }
    }
