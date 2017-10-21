    static void throwError(Result r) throws SQLException {

//#ifdef JAVA6
        throw sqlException(r.getMainString(), r.getSubString(),
                           r.getErrorCode(), r.getException());

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
