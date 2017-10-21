    public int read(final byte[] buf, final int off,
                    int len) throws IOException {

        if (buf == null) {
            throw new NullPointerException();
        }

        if (m_count <= 0) {
            return -1;
        }

        if (len > m_count) {
            len = (int) m_count;
        }

        final int r = m_input.read(buf, off, len);

        if (r > 0) {
            m_count -= r;
        }

        return r;
    }
