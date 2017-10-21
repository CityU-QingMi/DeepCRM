    private byte[] slurp(final File file) throws Exception {
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            final byte[] result = new byte[(int) file.length()];
            int pos = 0;
            int length = in.read(result);
            while (length > 0) {
                pos += length;
                length = in.read(result, pos, result.length - pos);
            }
            return result;
        } finally {
            Closer.closeSilently(in);
        }
    }
