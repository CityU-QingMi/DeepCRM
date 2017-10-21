    public int read(final byte[] buf) throws IOException {

        if (buf == null) {
            throw new NullPointerException();
        }

        if (m_count <= 0) {
            return -1;
        }

        int len = buf.length;

        if (len > m_count) {
            len = (int) m_count;
        }

        final int r = m_input.read(buf, 0, len);

        if (r > 0) {
            m_count -= r;
        }

        return r;
    }
