    public void logStatementEvent(Session session, Statement statement,
                                  Object[] paramValues, Result result,
                                  int level) {

        if (sqlLog != null && level <= propSqlLogLevel) {
            String sessionId   = Long.toString(session.getId());
            String sql         = statement.getSQL();
            String values      = "";
            int    paramLength = 0;

            if (propSqlLogLevel < SimpleLog.LOG_DETAIL) {
                if (sql.length() > 256) {
                    sql = sql.substring(0, 256);
                }

                paramLength = 32;
            }

            if (paramValues != null && paramValues.length > 0) {
                values = RowType.convertToSQLString(
                    paramValues,
                    statement.getParametersMetaData().getParameterTypes(),
                    paramLength);
            }

            if (propSqlLogLevel == SimpleLog.LOG_RESULT) {
                StringBuffer sb = new StringBuffer(values);

                sb.append(' ').append('[');

                if (result.isError()) {
                    sb.append(result.getErrorCode());
                } else if (result.isData()) {
                    sb.append(result.getNavigator().getSize());
                } else if (result.isUpdateCount()) {
                    sb.append(result.getUpdateCount());
                }

                sb.append(']');

                values = sb.toString();
            }

            sqlLog.logContext(level, sessionId, sql, values);
        }
    }
