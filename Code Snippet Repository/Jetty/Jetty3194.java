        public PreparedStatement getUpdateSessionStatement(Connection connection, String id, SessionContext context)
                throws SQLException
        {
            String s =  "update "+getSchemaTableName()+
                    " set "+getLastNodeColumn()+" = ?, "+getAccessTimeColumn()+" = ?, "+
                    getLastAccessTimeColumn()+" = ?, "+getLastSavedTimeColumn()+" = ?, "+getExpiryTimeColumn()+" = ?, "+
                    getMaxIntervalColumn()+" = ?, "+getMapColumn()+" = ? where "+getIdColumn()+" = ? and "+getContextPathColumn()+
                    " = ? and "+getVirtualHostColumn()+" = ?";

            String cp = context.getCanonicalContextPath();
            if (_dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp))
               cp = NULL_CONTEXT_PATH;
  
            PreparedStatement statement = connection.prepareStatement(s);
            statement.setString(8, id);
            statement.setString(9, cp);
            statement.setString(10, context.getVhost());
            return statement;
        }
