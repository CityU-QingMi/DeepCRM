    private Integer getMetadataByAssignableType(Class<?> type)
    {
        for (Map.Entry<Class<?>, Integer> entry : typeMap.entrySet())
        {
            if (entry.getKey().isAssignableFrom(type))
            {
                return entry.getValue();
            }
        }

        return null;
    }
