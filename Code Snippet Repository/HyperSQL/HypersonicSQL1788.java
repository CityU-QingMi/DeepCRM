    synchronized public void reset() {

        if (userConnection != null) {

            // userConnection is already closed in normal use
            try {
                userConnection.close();
            } catch (SQLException e) {

                // check connection problems
            }
        }

        try {
            connection.reset();
        } catch (SQLException e) {

            // check connection problems
        }

        isInUse = false;
    }
