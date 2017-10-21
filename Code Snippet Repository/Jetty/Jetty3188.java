    @Override
    public boolean delete(String id) throws Exception
    {   
        try (Connection connection = _dbAdaptor.getConnection();
             PreparedStatement statement = _sessionTableSchema.getDeleteStatement(connection, id, _context))
        {
            connection.setAutoCommit(true);
            int rows = statement.executeUpdate();
            if (LOG.isDebugEnabled())
                LOG.debug("Deleted Session {}:{}",id,(rows>0));
            
            return rows > 0;
        }
    }
