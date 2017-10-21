    public static byte[] copyBytes(byte[] source, byte[] dest,
                                   int destOffset) {

        if (source.length + destOffset > dest.length) {
            byte[] newDest = new byte[source.length + destOffset];

            System.arraycopy(dest, 0, newDest, 0, dest.length);

            dest = newDest;
        }

        System.arraycopy(source, 0, dest, destOffset, source.length);

        return dest;
    }
