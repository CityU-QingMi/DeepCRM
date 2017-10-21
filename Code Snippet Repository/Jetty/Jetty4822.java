    public static String toDetailString(ByteBuffer buffer)
    {
        if (buffer == null)
            return "null";

        StringBuilder buf = new StringBuilder();
        idString(buffer,buf);
        buf.append("[p=");
        buf.append(buffer.position());
        buf.append(",l=");
        buf.append(buffer.limit());
        buf.append(",c=");
        buf.append(buffer.capacity());
        buf.append(",r=");
        buf.append(buffer.remaining());
        buf.append("]={");

        appendDebugString(buf,buffer);

        buf.append("}");

        return buf.toString();
    }
