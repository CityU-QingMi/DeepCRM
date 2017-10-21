        public PreparedStatement getCheckSessionExistsStatement (Connection connection, SessionContext context)
        throws SQLException
        {
            if (_dbAdaptor == null)
                throw new IllegalStateException("No DB adaptor");

            String cp = context.getCanonicalContextPath();
            if (_dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp))
                    cp = NULL_CONTEXT_PATH;

            PreparedStatement statement = connection.prepareStatement("select "+getIdColumn()+", "+getExpiryTimeColumn()+
                                                                      " from "+getSchemaTableName()+
                                                                      " where "+getIdColumn()+" = ? and "+
                                                                      getContextPathColumn()+" = ? and "+
                                                                      getVirtualHostColumn()+" = ?");
            statement.setString(2, cp);
            statement.setString(3, context.getVhost());
            return statement;
        }
