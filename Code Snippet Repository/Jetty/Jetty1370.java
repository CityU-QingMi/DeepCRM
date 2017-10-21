    public boolean put(PathSpec pathSpec, E resource)
    {
        MappedResource<E> entry = new MappedResource<>(pathSpec,resource);
        switch (pathSpec.group)
        {
            case EXACT:
                String exact = pathSpec.getPrefix();
                while (exact!=null && !_exactMap.put(exact,entry))
                    _exactMap=new ArrayTernaryTrie<>((ArrayTernaryTrie<MappedResource<E>>)_exactMap,1.5);
                break;
            case PREFIX_GLOB:
                String prefix = pathSpec.getPrefix();
                while (prefix!=null && !_prefixMap.put(prefix,entry))
                    _prefixMap=new ArrayTernaryTrie<>((ArrayTernaryTrie<MappedResource<E>>)_prefixMap,1.5);
                break;
            case SUFFIX_GLOB:
                String suffix = pathSpec.getSuffix();
                while (suffix!=null && !_suffixMap.put(suffix,entry))
                    _suffixMap=new ArrayTernaryTrie<>((ArrayTernaryTrie<MappedResource<E>>)_prefixMap,1.5);
                break;
            default:
        }
        
        boolean added =_mappings.add(entry);
        if (LOG.isDebugEnabled())
            LOG.debug("{} {} to {}",added?"Added":"Ignored",entry,this);
        return added;
    }
