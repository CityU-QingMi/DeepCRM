    public void prependFilterMapping (FilterMapping mapping)
    {
        if (mapping != null)
        {
            Source source = (mapping.getFilterHolder()==null?null:mapping.getFilterHolder().getSource());
            FilterMapping[] mappings = getFilterMappings();
            if (mappings==null || mappings.length==0)
            {
                setFilterMappings(insertFilterMapping(mapping, 0, false));
                if (source != null && Source.JAVAX_API == source)
                    _matchBeforeIndex = 0;
            }
            else
            {
                if (source != null && Source.JAVAX_API == source)
                {
                    //programmatically defined filter mappings are prepended to mapping list in the order
                    //in which they were defined. In other words, insert this mapping at the tail of the 
                    //programmatically prepended filter mappings, BEFORE the first web.xml defined filter mapping.

                    if (_matchBeforeIndex < 0)
                    { 
                        //no programmatically defined prepended filter mappings yet, prepend this one
                        _matchBeforeIndex = 0;
                        FilterMapping[] new_mappings = insertFilterMapping(mapping, 0, true);
                        setFilterMappings(new_mappings);
                    }
                    else
                    {
                        FilterMapping[] new_mappings = insertFilterMapping(mapping,_matchBeforeIndex, false);
                        ++_matchBeforeIndex;
                        setFilterMappings(new_mappings);
                    }
                }
                else
                {
                    //non programmatically defined, just prepend to list
                    FilterMapping[] new_mappings = insertFilterMapping(mapping, 0, true);
                    setFilterMappings(new_mappings);
                }
                
                //adjust matchAfterIndex ptr to take account of the mapping we just prepended
                if (_matchAfterIndex >= 0)
                    ++_matchAfterIndex;
            }
        }
    }
