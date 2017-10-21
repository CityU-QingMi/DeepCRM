    boolean appliesTo(String path, int type)
    {
        if (appliesTo(type))
        {
            for (int i=0;i<_pathSpecs.length;i++)
                if (_pathSpecs[i]!=null &&  PathMap.match(_pathSpecs[i], path,true))
                    return true;
        }

        return false;
    }
