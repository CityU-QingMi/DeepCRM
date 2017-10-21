    @Override
    public O remove(Object pathSpec)
    {
        if (pathSpec!=null)
        {
            String spec=(String) pathSpec;
            if (spec.equals("/*"))
                _prefixDefault=null;
            else if (spec.endsWith("/*"))
                _prefixMap.remove(spec.substring(0,spec.length()-2));
            else if (spec.startsWith("*."))
                _suffixMap.remove(spec.substring(2));
            else if (spec.equals(URIUtil.SLASH))
            {
                _default=null;
                _defaultSingletonList=null;
            }
            else
                _exactMap.remove(spec);
        }
        return super.remove(pathSpec);
    }
