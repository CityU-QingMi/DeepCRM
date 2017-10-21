    private boolean containsNonDefaultMappings (List<ServletMapping> mappings)
    {
        if (mappings == null)
            return false;
        for (ServletMapping m:mappings)
        {
            if (!m.isDefault())
                return true;
        }
        return false;
    }
