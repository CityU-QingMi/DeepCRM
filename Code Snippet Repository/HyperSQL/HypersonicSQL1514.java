    public synchronized void free() throws SQLException {

        if (m_closed) {
            return;
        }

        m_closed = true;

        final HsqlList streams = m_streams;
        m_streams = null;


        for (org.hsqldb.lib.Iterator itr = streams.iterator(); itr.hasNext();) {
            final Object stream = itr.next();
            closeSafely(stream);
        }

        if (m_deleteOnFree) {
            try {
                m_file.delete();
            } catch (SecurityException e) {
            }
        }
    }
