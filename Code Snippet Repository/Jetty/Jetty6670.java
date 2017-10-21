    @Override
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Utf8CharBuffer@").append(hashCode());
        str.append("[p=").append(buffer.position());
        str.append(",l=").append(buffer.limit());
        str.append(",c=").append(buffer.capacity());
        str.append(",r=").append(buffer.remaining());
        str.append("]");
        return str.toString();
    }
