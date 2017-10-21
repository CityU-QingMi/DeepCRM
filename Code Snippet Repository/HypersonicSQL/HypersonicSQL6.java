        public String getLimitString(String sql, boolean hasOffset) {
                if ( hsqldbVersion < 20 ) {
                        return new StringBuffer( sql.length() + 10 )
                                        .append( sql )
                                        .insert(
                                                        sql.toLowerCase().indexOf( "select" ) + 6,
                                                        hasOffset ? " limit ? ?" : " top ?"
                                        )
                                        .toString();
                }
                else {
                        return new StringBuffer( sql.length() + 20 )
                                        .append( sql )
                                        .append( hasOffset ? " offset ? limit ?" : " limit ?" )
                                        .toString();
                }
        }
