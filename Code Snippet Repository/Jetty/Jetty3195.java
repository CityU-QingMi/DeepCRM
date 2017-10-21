        public PreparedStatement getExpiredSessionsStatement (Connection connection, String canonicalContextPath, String vhost, long expiry)
        throws SQLException
        {
            if (_dbAdaptor == null)
                throw new IllegalStateException("No DB adaptor");

            String cp = canonicalContextPath;
            if (_dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp))
                    cp = NULL_CONTEXT_PATH;

            PreparedStatement statement = connection.prepareStatement("select "+getIdColumn()+", "+getExpiryTimeColumn()+
                                                                      " from "+getSchemaTableName()+" where "+getContextPathColumn()+" = ? and "+
                                                                      getVirtualHostColumn()+" = ? and "+
                                                                      getExpiryTimeColumn()+" >0 and "+getExpiryTimeColumn()+" <= ?");

            statement.setString(1, cp);
            statement.setString(2, vhost);
            statement.setLong(3, expiry);
            return statement;
        }
