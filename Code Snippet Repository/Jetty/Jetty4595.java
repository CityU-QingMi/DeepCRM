    public Props getProps()
    {
        Props props = new Props();

        // add all properties from config sources (in reverse order)
        ListIterator<ConfigSource> iter = sources.listIterator(sources.size());
        while (iter.hasPrevious())
        {
            ConfigSource source = iter.previous();
            props.addAll(source.getProps());
        }
        return props;
    }
