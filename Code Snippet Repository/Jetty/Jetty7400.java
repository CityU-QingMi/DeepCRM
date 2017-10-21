        public H1Client()
        {
            for (Connector c:__server.getConnectors())
            {
                if (c instanceof NetworkConnector && c.getDefaultConnectionFactory().getProtocol().equals(HttpVersion.HTTP_1_1.asString()))
                {
                    _connector=(NetworkConnector)c;
                    break;
                }
            }
        }
