    private static void idString(ByteBuffer buffer, StringBuilder out) 
    {
        out.append(buffer.getClass().getSimpleName());
        out.append("@");
        if (buffer.hasArray() && buffer.arrayOffset()==4)
        {
            out.append('T');
            byte[] array = buffer.array();
            TypeUtil.toHex(array[0],out);
            TypeUtil.toHex(array[1],out);
            TypeUtil.toHex(array[2],out);
            TypeUtil.toHex(array[3],out);
        }
        else
            out.append(Integer.toHexString(System.identityHashCode(buffer)));
    }
