    public void parse(ConfigSources sources)
    {
        ListIterator<ConfigSource> iter = sources.reverseListIterator();
        while (iter.hasPrevious())
        {
            ConfigSource source = iter.previous();
            for (RawArgs.Entry arg : source.getArgs())
            {
                parse(arg.getLine(),arg.getOrigin());
            }
        }
    }
