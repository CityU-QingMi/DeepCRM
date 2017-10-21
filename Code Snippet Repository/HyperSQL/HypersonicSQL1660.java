    public void close() throws SQLException {

        if (navigator == null) {
            return;
        }

        if (ResultProperties.isHeld(rsProperties)) {
            session.closeNavigator(navigator.getId());
        } else {
            navigator.release();
        }

        navigator = null;

        if (autoClose && statement != null) {
            statement.close();
        }
    }
