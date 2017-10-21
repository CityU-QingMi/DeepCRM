        public H1SClient()
        {
            for (Connector c:__server.getConnectors())
            {
                if (c instanceof NetworkConnector && c.getDefaultConnectionFactory().getProtocol().equals("SSL"))
                {
                    _connector=(NetworkConnector)c;
                    break;
                }
            }
        }
