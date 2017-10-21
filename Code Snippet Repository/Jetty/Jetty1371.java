    @SuppressWarnings("")
    public boolean remove(PathSpec pathSpec)
    {
        switch (pathSpec.group)
        {
            case EXACT:
                _exactMap.remove(pathSpec.getPrefix());
                break;
            case PREFIX_GLOB:
                _prefixMap.remove(pathSpec.getPrefix());
                break;
            case SUFFIX_GLOB:
                _suffixMap.remove(pathSpec.getSuffix());
                break;
        }
        
        Iterator<MappedResource<E>> iter = _mappings.iterator();
        boolean removed=false;
        while (iter.hasNext())
        {
            if (iter.next().getPathSpec().equals(pathSpec))
            {
                removed=true;
                iter.remove();
                break;
            }
        }
        if (LOG.isDebugEnabled())
            LOG.debug("{} {} to {}",removed?"Removed":"Ignored",pathSpec,this);
        return removed;
    }
