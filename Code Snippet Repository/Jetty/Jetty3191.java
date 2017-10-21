    private void doUpdate (String id, SessionData data)
            throws Exception
    {
        try (Connection connection = _dbAdaptor.getConnection())        
        {
            connection.setAutoCommit(true);
            try (PreparedStatement statement = _sessionTableSchema.getUpdateSessionStatement(connection, data.getId(), _context))
            {
                statement.setString(1, data.getLastNode());//should be my node id
                statement.setLong(2, data.getAccessed());//accessTime
                statement.setLong(3, data.getLastAccessed()); //lastAccessTime
                statement.setLong(4, data.getLastSaved()); //last saved time
                statement.setLong(5, data.getExpiry());
                statement.setLong(6, data.getMaxInactiveMs());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(data.getAllAttributes());
                oos.flush();
                byte[] bytes = baos.toByteArray();
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                statement.setBinaryStream(7, bais, bytes.length);//attribute map as blob
                statement.executeUpdate();

                if (LOG.isDebugEnabled())
                    LOG.debug("Updated session "+data);
            }
        }
    }
