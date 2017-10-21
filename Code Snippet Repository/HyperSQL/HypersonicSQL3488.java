    protected boolean test(Statement aStatement) {

        try {
            aStatement.execute(getSql());
        } catch (SQLException sqlX) {
            caught = sqlX;

            if (expectedState == null
                    || expectedState.equalsIgnoreCase(sqlX.getSQLState())) {
                return true;
            }

            message = "SQLState '" + sqlX.getSQLState() + "' : "
                      + sqlX.toString() + " instead of '" + expectedState
                      + "'";
        } catch (Exception x) {
            caught  = x;
            message = x.toString();
        }

        return false;
    }
