        public PreparedStatement getUpdateStatement (Connection connection, String id, SessionContext contextId)
        throws SQLException
        {
            if (_dbAdaptor == null)
                throw new IllegalStateException("No DB adaptor");

            String cp = contextId.getCanonicalContextPath();
            if (_dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp))
                    cp = NULL_CONTEXT_PATH;
            
            String s = "update "+getSchemaTableName()+
                    " set "+getLastNodeColumn()+" = ?, "+getAccessTimeColumn()+" = ?, "+
                    getLastAccessTimeColumn()+" = ?, "+getLastSavedTimeColumn()+" = ?, "+getExpiryTimeColumn()+" = ?, "+
                    getMaxIntervalColumn()+" = ?, "+getMapColumn()+" = ? where "+getIdColumn()+" = ? and "+getContextPathColumn()+
                    " = ? and "+getVirtualHostColumn()+" = ?";


            PreparedStatement statement = connection.prepareStatement(s);
            statement.setString(8, id);
            statement.setString(9, cp);
            statement.setString(10, contextId.getVhost());

            return statement;
        }
