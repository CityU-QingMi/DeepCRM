    synchronized public void release() {

        if (userConnection != null) {

            // userConnection is already closed in normal use
            try {
                userConnection.close();
            } catch (SQLException e) {

                // check connection problems
            }
        }

        try {
            connection.close();
        } catch (SQLException e) {

            // check connection problems
        }

        isInUse = false;
    }
