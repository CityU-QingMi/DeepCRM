    public List<M> addAll(List<Class<? extends T>> coders)
    {
        List<M> metadatas = new ArrayList<>();

        for (Class<? extends T> coder : coders)
        {
            metadatas.addAll(discover(coder));
        }

        trackMetadata(metadatas);
        return metadatas;
    }
