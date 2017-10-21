                public String extractConstraintName(SQLException sqle) {
                        String constraintName = null;

                        int errorCode = JDBCExceptionHelper.extractErrorCode( sqle );

                        if ( errorCode == -8 ) {
                                constraintName = extractUsingTemplate(
                                                "; ", " table: ", sqle.getMessage()
                                );
                        }
                        else if ( errorCode == -9 ) {
                                constraintName = extractUsingTemplate(
                                                "; ", " table: ", sqle.getMessage()
                                );
                        }
                        else if ( errorCode == -104 ) {
                                constraintName = extractUsingTemplate(
                                                "; ", " table: ", sqle.getMessage()
                                );
                        }
                        else if ( errorCode == -177 ) {
                                constraintName = extractUsingTemplate(
                                                "; ", " table: ", sqle.getMessage()
                                );
                        }
                        return constraintName;
                }
