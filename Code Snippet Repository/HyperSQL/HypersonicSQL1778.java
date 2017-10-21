    static void throwError(HsqlException e) throws SQLException {

//#ifdef JAVA6
        throw sqlException(e.getMessage(), e.getSQLState(), e.getErrorCode(),
                           e);

//#else
/**/
/**/
/**/
/**/
/**/
/**/
/**/
/**/

//#endif JAVA6
    }
