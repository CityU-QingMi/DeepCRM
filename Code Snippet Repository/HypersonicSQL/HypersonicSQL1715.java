    public JDBCRowId(final String hex) throws SQLException {

        if (hex == null) {
            throw JDBCUtil.nullArgument("hex");
        }

        try {
            this.id = StringConverter.hexStringToByteArray(hex);
        } catch (IOException e) {
            throw JDBCUtil.sqlException(ErrorCode.JDBC_INVALID_ARGUMENT,
                                    "hex: " + e);

            // .illegalHexadecimalCharacterSequenceArgumentException("hex", e);
        }
    }
