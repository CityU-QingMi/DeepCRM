    static SQLException notSupported() {

//#ifdef JAVA6
        HsqlException e = Error.error(ErrorCode.X_0A000);

        return new SQLFeatureNotSupportedException(e.getMessage(),
                e.getSQLState(), -ErrorCode.X_0A000);

//#else
/**/
/**/
/**/

//#endif JAVA6
    }
