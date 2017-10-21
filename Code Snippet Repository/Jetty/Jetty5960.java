    public String getResourceAlias(String path)
    {
        if (_resourceAliases == null)
            return null;
        String alias = _resourceAliases.get(path);

        int slash=path.length();
        while (alias==null)
        {
            slash=path.lastIndexOf("/",slash-1);
            if (slash<0)
                break;
            String match=_resourceAliases.get(path.substring(0,slash+1));
            if (match!=null)
                alias=match+path.substring(slash+1);
        }
        return alias;
    }
