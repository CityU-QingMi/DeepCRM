    protected boolean test(Statement aStatement) {

        try {
            String sql = getSql();

            aStatement.execute(sql);
        } catch (Exception x) {
            message = x.toString();

            return false;
        }

        return true;
    }
