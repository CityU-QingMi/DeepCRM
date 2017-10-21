        public void flushToServer(TLSRecord record, long sleep) throws Exception
        {
            if (record == null)
            {
                server.shutdownOutput();
                if (client.isOutputShutdown())
                {
                    client.close();
                    server.close();
                }
            }
            else
            {
                flush(sleep, server, record.getBytes());
            }
        }
