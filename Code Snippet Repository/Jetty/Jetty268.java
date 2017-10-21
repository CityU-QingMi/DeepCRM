    private void process()
    {
        while (true)
        {
            Connection connection = connectionPool.acquire();
            if (connection == null)
                break;
            boolean proceed = process(connection);
            if (!proceed)
                break;
        }
    }
