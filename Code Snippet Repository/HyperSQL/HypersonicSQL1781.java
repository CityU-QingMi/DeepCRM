    public static SQLException sqlException(HsqlException e,
            Throwable cause) {

//#ifdef JAVA6
        return sqlException(e.getMessage(), e.getSQLState(), e.getErrorCode(),
                            cause);

//#else
/**/
/**/
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
