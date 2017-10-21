    public JDBCCallableStatement(
            JDBCConnection c, String sql, int resultSetType,
            int resultSetConcurrency,
            int resultSetHoldability) throws HsqlException, SQLException {

        super(c, sql, resultSetType, resultSetConcurrency,
              resultSetHoldability, ResultConstants.RETURN_NO_GENERATED_KEYS,
              null, null);

        String[] names;
        String   name;

        // outRegistrationMap = new IntKeyIntValueHashMap();
        parameterNameMap = new IntValueHashMap();

        if (parameterMetaData != null) {
            names = parameterMetaData.columnLabels;

            for (int i = 0; i < names.length; i++) {
                name = names[i];

                // PRE:  should never happen in practice
                if (name == null || name.length() == 0) {
                    continue;    // throw?
                }
                parameterNameMap.put(name, i);
            }
        }
    }
