    public String toHeaderRangeString(long size)
    {
        StringBuilder sb = new StringBuilder(40);
        sb.append("bytes ");
        sb.append(getFirst(size));
        sb.append('-');
        sb.append(getLast(size));
        sb.append("/");
        sb.append(size);
        return sb.toString();
    }
