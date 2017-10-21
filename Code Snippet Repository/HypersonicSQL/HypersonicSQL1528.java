    private void closeSafely(final Object target) {
        if (target instanceof RandomAccessFile) {
            closeSafely((RandomAccessFile) target);
        } else if (target instanceof InputStream) {
            closeSafely((InputStream) target);
        } else if (target instanceof OutputStream) {
            closeSafely((OutputStream) target);
        } else if (target instanceof Reader) {
            closeSafely((Reader) target);
        } else if (target instanceof Writer) {
            closeSafely((Writer) target);
        } else if (target instanceof Scanner) {
            closeSafely((Scanner) target);
        }
    }
