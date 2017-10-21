    public boolean addPossibleProperty(String arg, String source)
    {
        // Start property (syntax similar to System property)
        if (arg.startsWith("-D"))
        {
            String[] assign = arg.substring(2).split("=",2);
            switch (assign.length)
            {
                case 2:
                    setSystemProperty(assign[0],assign[1]);
                    setProperty(assign[0],assign[1],source);
                    return true;
                case 1:
                    setSystemProperty(assign[0],"");
                    setProperty(assign[0],"",source);
                    return true;
                default:
                    return false;
            }
        }

        // Is this a raw property declaration?
        int idx = arg.indexOf('=');
        if (idx >= 0)
        {
            String key = arg.substring(0,idx);
            String value = arg.substring(idx + 1);

            setProperty(key,value,source);
            return true;
        }

        // All other strings are ignored
        return false;
    }
