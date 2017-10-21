    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getClass().getSimpleName());
        builder.append("[metadatas=");
        builder.append(metadatas.size());
        builder.append(",coders=");
        builder.append(coders.size());
        builder.append("]");
        return builder.toString();
    }
