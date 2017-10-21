    private void doInsert (String id, SessionData data) 
    throws Exception
    {
        String s = _sessionTableSchema.getInsertSessionStatementAsString();


        try (Connection connection = _dbAdaptor.getConnection())        
        {
            connection.setAutoCommit(true);
            try  (PreparedStatement statement = connection.prepareStatement(s))
            {
                statement.setString(1, id); //session id
                
                String cp = _context.getCanonicalContextPath();
                if (_dbAdaptor.isEmptyStringNull() && StringUtil.isBlank(cp))
                        cp = NULL_CONTEXT_PATH;
                
                statement.setString(2, cp); //context path
                      
                statement.setString(3, _context.getVhost()); //first vhost
                statement.setString(4, data.getLastNode());//my node id
                statement.setLong(5, data.getAccessed());//accessTime
                statement.setLong(6, data.getLastAccessed()); //lastAccessTime
                statement.setLong(7, data.getCreated()); //time created
                statement.setLong(8, data.getCookieSet());//time cookie was set
                statement.setLong(9, data.getLastSaved()); //last saved time
                statement.setLong(10, data.getExpiry());
                statement.setLong(11, data.getMaxInactiveMs());

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(data.getAllAttributes());
                oos.flush();
                byte[] bytes = baos.toByteArray();

                ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                statement.setBinaryStream(12, bais, bytes.length);//attribute map as blob
                statement.executeUpdate();
                if (LOG.isDebugEnabled())
                    LOG.debug("Inserted session "+data);
            }
        }
    }
