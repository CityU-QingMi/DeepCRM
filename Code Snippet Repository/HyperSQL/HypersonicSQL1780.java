    public static SQLException sqlException(HsqlException e) {

//#ifdef JAVA6
        return sqlException(e.getMessage(), e.getSQLState(), e.getErrorCode(),
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
