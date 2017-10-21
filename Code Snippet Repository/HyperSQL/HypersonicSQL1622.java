    public void close(int wait) throws SQLException {

        if (wait <0 || wait > 60) {
            throw JDBCUtil.outOfRangeArgument();
        }
        if (closed) {
            return;
        }

        closed = true;

        try {
            Thread.sleep(1000 * wait);
        } catch (Throwable t) {}

        for (int i = 0; i < connections.length; i++) {
            if (connections[i] != null) {
                connections[i].release();
            }
        }

        for (int i = 0; i < connections.length; i++) {
            connections[i] = null;
        }

    }
