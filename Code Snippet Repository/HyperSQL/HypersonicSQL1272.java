    public static byte[] streamToBytes(InputStream is) throws IOException {
        byte[]                xferBuffer = new byte[10240];
        byte[]                outBytes = null;
        int                   i;
        ByteArrayOutputStream baos       = new ByteArrayOutputStream();

        try {
            while ((i = is.read(xferBuffer)) > 0) baos.write(xferBuffer, 0, i);
            outBytes = baos.toByteArray();
        } finally {
            baos = null;  // Encourage buffer GC
        }
        return outBytes;
    }
