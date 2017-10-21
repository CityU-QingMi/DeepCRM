    public void addFilterMapping (FilterMapping mapping)
    {
        if (mapping != null)
        {
            Source source = (mapping.getFilterHolder()==null?null:mapping.getFilterHolder().getSource());
            FilterMapping[] mappings =getFilterMappings();
            if (mappings==null || mappings.length==0)
            {
                setFilterMappings(insertFilterMapping(mapping,0,false));
                if (source != null && source == Source.JAVAX_API)
                    _matchAfterIndex = 0;
            }
            else
            {
                //there are existing entries. If this is a programmatic filtermapping, it is added at the end of the list.
                //If this is a normal filtermapping, it is inserted after all the other filtermappings (matchBefores and normals), 
                //but before the first matchAfter filtermapping.
                if (source != null && Source.JAVAX_API == source)
                {
                    setFilterMappings(insertFilterMapping(mapping,mappings.length-1, false));
                    if (_matchAfterIndex < 0)
                        _matchAfterIndex = getFilterMappings().length-1;
                }
                else
                {
                    //insert non-programmatic filter mappings before any matchAfters, if any
                    if (_matchAfterIndex < 0)
                        setFilterMappings(insertFilterMapping(mapping,mappings.length-1, false));
                    else
                    {
                        FilterMapping[] new_mappings = insertFilterMapping(mapping, _matchAfterIndex, true);
                        ++_matchAfterIndex;
                        setFilterMappings(new_mappings);
                    }
                }
            }
        }
    }
