    public static byte[] loadBinary(File binFile) throws IOException {
        byte[]                xferBuffer = new byte[10240];
        byte[]                outBytes = null;
        ByteArrayOutputStream baos;
        int                   i;
        FileInputStream       fis        = new FileInputStream(binFile);

        try {
            baos = new ByteArrayOutputStream();
            while ((i = fis.read(xferBuffer)) > 0) baos.write(xferBuffer, 0, i);
            outBytes = baos.toByteArray();
        } finally {
            try {
                fis.close();
            } catch (IOException ioe) {
                // intentionally empty
            } finally {
                fis = null; // Encourage GC of buffers
                baos = null; // Encourage GC of buffers
            }
        }

        return outBytes;
    }
