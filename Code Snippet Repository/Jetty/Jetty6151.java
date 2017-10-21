    public List<M> getMetadataByImplementation(Class<? extends T> clazz)
    {
        List<Integer> indexes = implMap.get(clazz);
        if (indexes == null)
        {
            return null;
        }
        List<M> ret = new ArrayList<>();
        for (Integer idx : indexes)
        {
            ret.add(metadatas.get(idx));
        }
        return ret;
    }
