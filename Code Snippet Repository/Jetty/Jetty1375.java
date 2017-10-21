    private PathSpec asPathSpec(Object o)
    {
        if (o == null)
        {
            return null;
        }
        if (o instanceof PathSpec)
        {
            return (PathSpec)o;
        }
        if (o instanceof String)
        {
            return PathMappings.asPathSpec((String)o);
        }
        return PathMappings.asPathSpec(o.toString());
    }
