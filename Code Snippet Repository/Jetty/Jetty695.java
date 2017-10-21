        public void flushToClient(TLSRecord record) throws Exception
        {
            if (record == null)
            {
                client.shutdownOutput();
                if (server.isOutputShutdown())
                {
                    server.close();
                    client.close();
                }
            }
            else
            {
                flush(0, client, record.getBytes());
            }
        }
