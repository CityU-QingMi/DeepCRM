    public static Result newConnectionAttemptRequest(String user,
            String password, String database, String zoneString,
            int timeZoneSeconds) {

        Result result = newResult(ResultConstants.CONNECT);

        result.mainString   = user;
        result.subString    = password;
        result.zoneString   = zoneString;
        result.databaseName = database;
        result.updateCount  = timeZoneSeconds;

        return result;
    }
