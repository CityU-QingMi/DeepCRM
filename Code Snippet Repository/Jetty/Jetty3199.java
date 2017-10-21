        public PreparedStatement getLoadStatement (Connection connection, String id, SessionContext contextId)
        throws SQLException
        { 
            if (_dbAdaptor == null)
                throw new IllegalStateException("No DB adaptor");


            String cp = contextId.getCanonicalContextPath();
            if (_dbAdaptor.isEmptyStringNull()&& StringUtil.isBlank(cp))
                    cp = NULL_CONTEXT_PATH;

            PreparedStatement statement = connection.prepareStatement("select * from "+getSchemaTableName()+
                                                                      " where "+getIdColumn()+" = ? and "+getContextPathColumn()+
                                                                      " = ? and "+getVirtualHostColumn()+" = ?");
            statement.setString(1, id);
            statement.setString(2, cp);
            statement.setString(3, contextId.getVhost());

            return statement;
        }
