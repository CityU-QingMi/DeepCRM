    @Override
    protected void connectAndStart() {
        try {
            this.connection = this.connectionSource.getConnection();
            this.connection.setAutoCommit(false);
            this.statement = this.connection.prepareStatement(this.sqlStatement);
        } catch (final SQLException e) {
            throw new AppenderLoggingException(
                    "Cannot write logging event or flush buffer; JDBC manager cannot connect to the database.", e
            );
        }
    }
