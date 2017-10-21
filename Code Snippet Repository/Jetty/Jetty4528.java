    public Set<String> enable(String name, String enabledFrom)
    {
        Module module = get(name);
        if (module==null)
            throw new UsageException(UsageException.ERR_UNKNOWN,"Unknown module='%s'. List available with --list-modules",name);

        Set<String> enabled = new HashSet<>();
        enable(enabled,module,enabledFrom,false);
        return enabled;
    }
