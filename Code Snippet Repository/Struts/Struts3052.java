    public void close() throws IOException {
        if (response == null || closed)
            // multiple calls to close is OK
            return;
        flush();
        if (out != null)
            out.close();
        out = null;
        closed = true;
    }
