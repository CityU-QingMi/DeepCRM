    @Override
    public int read(final CharBuffer target) throws IOException {
        final int len = target.remaining();
        final char[] cbuf = new char[len];
        final int charsRead = read(cbuf, 0, len);
        if (charsRead > 0) {
            target.put(cbuf, 0, charsRead);
        }
        return charsRead;
    }
