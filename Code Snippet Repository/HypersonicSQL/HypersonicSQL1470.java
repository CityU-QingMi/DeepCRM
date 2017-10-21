    public synchronized void free() throws SQLException {

        if (m_closed) {
            return;
        }

        m_closed = true;

        final List<Object> streams = new ArrayList<Object>(m_streams);

        m_streams = null;

        for (Iterator<Object> itr = streams.iterator(); itr.hasNext(); ) {
            final Object stream = itr.next();

            closeSafely(stream);
        }

        if (m_deleteOnFree) {
            try {
                m_file.delete();
            } catch (SecurityException e) {}
        }
    }
