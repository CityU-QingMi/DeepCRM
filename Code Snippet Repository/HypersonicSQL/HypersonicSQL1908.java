    public int read() throws IOException {

        if (m_count <= 0) {
            return -1;
        }

        final int b = m_input.read();

        if (b >= 0) {
            m_count--;
        }

        return b;
    }
