    static void throwError(HsqlException e) throws SQLException {

//#ifdef JAVA6
        throw JDBCUtil.sqlException(e.getMessage(), e.getSQLState(),
            e.getErrorCode(), e);

//#else
/**/
/**/
/**/
/**/

//#endif JAVA6
    }
