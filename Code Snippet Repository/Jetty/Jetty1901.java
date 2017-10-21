    @Override
    public void destroy()
    {
        try
        {
            if (_shutdown!=null)
            {
                LOG.info("Shutdown datasource {}",_datasource);
                try (Connection connection = _datasource.getConnection();
                        Statement stmt = connection.createStatement())
                {
                    stmt.executeUpdate(_shutdown);
                }
            }
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }

        try
        {
            Method close = _datasource.getClass().getMethod("close", new Class[]{});
            LOG.info("Close datasource {}",_datasource);
            close.invoke(_datasource, new Object[]{});
        }
        catch (Exception e)
        {
            LOG.warn(e);
        }
    }
