    public M getMetadataByType(Class<?> type)
    {
        Integer idx = typeMap.get(type);
        if (idx == null)
        {
            // Quick lookup failed, try slower lookup via isAssignable instead
            idx = getMetadataByAssignableType(type);
            if (idx != null)
            {
                // add new entry map
                typeMap.put(type,idx);
            }
        }

        // If idx is STILL null, we've got no match
        if (idx == null)
        {
            return null;
        }
        return metadatas.get(idx);
    }
