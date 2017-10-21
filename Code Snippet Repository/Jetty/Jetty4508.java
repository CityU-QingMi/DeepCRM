    public Module(BaseHome basehome, Path path) throws FileNotFoundException, IOException
    {
        super();
        _path = path;
        
        // Module name is the / separated path below the modules directory
        int m=-1;
        for (int i=path.getNameCount();i-->0;)
        {
            if ("modules".equals(path.getName(i).toString()))
            {
                m=i;
                break;
            }
        }
        if (m<0)
            throw new IllegalArgumentException("Module not contained within modules directory: "+basehome.toShortForm(path));
        String n=path.getName(m+1).toString();
        for (int i=m+2;i<path.getNameCount();i++)
            n=n+"/"+path.getName(i).toString();
        Matcher matcher=MOD_NAME.matcher(n);
        if (!matcher.matches())
            throw new IllegalArgumentException("Module filename must have .mod extension: "+basehome.toShortForm(path));
        _name=matcher.group(1);
    
        _provides.add(_name);
        _dynamic=_name.contains("/");        
        
        process(basehome);
    }
