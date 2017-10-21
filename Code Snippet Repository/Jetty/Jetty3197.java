        public PreparedStatement getAllAncientExpiredSessionsStatement (Connection connection)
        throws SQLException
        {
            if (_dbAdaptor == null)
                throw new IllegalStateException("No DB adaptor");

            PreparedStatement statement = connection.prepareStatement("select "+getIdColumn()+", "+getContextPathColumn()+", "+getVirtualHostColumn()+
                                                                      " from "+getSchemaTableName()+
                                                                      " where "+getExpiryTimeColumn()+" >0 and "+getExpiryTimeColumn()+" <= ?");
            return statement;
        }
