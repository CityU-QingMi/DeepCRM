        public PreparedStatement getMyExpiredSessionsStatement (Connection connection, SessionContext sessionContext, long expiry)
        throws SQLException
        {
            if (_dbAdaptor == null)
                throw new IllegalStateException("No DB adaptor");

            String cp = sessionContext.getCanonicalContextPath();
            if (_dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp))
                    cp = NULL_CONTEXT_PATH;

            PreparedStatement statement = connection.prepareStatement("select "+getIdColumn()+", "+getExpiryTimeColumn()+
                                                                      " from "+getSchemaTableName()+" where "+
                                                                      getLastNodeColumn()+" = ? and "+
                                                                      getContextPathColumn()+" = ? and "+
                                                                      getVirtualHostColumn()+" = ? and "+
                                                                      getExpiryTimeColumn()+" >0 and "+getExpiryTimeColumn()+" <= ?");

            statement.setString(1, sessionContext.getWorkerName());
            statement.setString(2, cp);
            statement.setString(3,  sessionContext.getVhost());
            statement.setLong(4, expiry);
            return statement;
        }
