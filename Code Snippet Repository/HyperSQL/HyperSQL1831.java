    public void setEventLogLevel(int level, boolean logSql) {

        if (level < SimpleLog.LOG_NONE || level > SimpleLog.LOG_RESULT) {
            throw Error.error(ErrorCode.X_42556);
        }

        if (logSql) {
            propSqlLogLevel = level;

            sqlLog.setLevel(level);
        } else {
            if (level > SimpleLog.LOG_DETAIL) {
                level = SimpleLog.LOG_DETAIL;
            }

            propEventLogLevel = level;

            appLog.setLevel(level);
        }
    }
