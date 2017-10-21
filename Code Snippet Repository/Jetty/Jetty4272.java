    @ManagedAttribute("")
    public Map<String, String> getPushCache()
    {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<String, PrimaryResource> entry : _cache.entrySet())
        {
            PrimaryResource resource = entry.getValue();
            String value = String.format("size=%d: %s", resource._associated.size(), new TreeSet<>(resource._associated));
            result.put(entry.getKey(), value);
        }
        return result;
    }
