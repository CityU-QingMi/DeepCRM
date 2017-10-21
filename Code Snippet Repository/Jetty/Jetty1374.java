    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(this.getClass().getSimpleName()).append("[\"");
        str.append(pathSpec);
        str.append("\",pathDepth=").append(pathDepth);
        str.append(",group=").append(group);
        str.append("]");
        return str.toString();
    }
