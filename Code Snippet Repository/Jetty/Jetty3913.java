    protected FilterMapping[] insertFilterMapping (FilterMapping mapping, int pos, boolean before)
    {
        if (pos < 0)
            throw new IllegalArgumentException("FilterMapping insertion pos < 0");
        FilterMapping[] mappings = getFilterMappings();
        
        if (mappings==null || mappings.length==0)
        {
            return new FilterMapping[] {mapping};
        }
        FilterMapping[] new_mappings = new FilterMapping[mappings.length+1];

    
        if (before)
        {
            //copy existing filter mappings up to but not including the pos
            System.arraycopy(mappings,0,new_mappings,0,pos);

            //add in the new mapping
            new_mappings[pos] = mapping; 

            //copy the old pos mapping and any remaining existing mappings
            System.arraycopy(mappings,pos,new_mappings,pos+1, mappings.length-pos);

        }
        else
        {
            //copy existing filter mappings up to and including the pos
            System.arraycopy(mappings,0,new_mappings,0,pos+1);
            //add in the new mapping after the pos
            new_mappings[pos+1] = mapping;   

            //copy the remaining existing mappings
            if (mappings.length > pos+1)
                System.arraycopy(mappings,pos+1,new_mappings,pos+2, mappings.length-(pos+1));
        }
        return new_mappings;
    }
