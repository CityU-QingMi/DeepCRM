        private void tunnel()
        {
            try
            {
                HttpDestination destination = (HttpDestination)context.get(HttpClientTransport.HTTP_DESTINATION_CONTEXT_KEY);
                HttpClient client = destination.getHttpClient();
                ClientConnectionFactory connectionFactory = this.connectionFactory;
                if (destination.isSecure())
                    connectionFactory = client.newSslClientConnectionFactory(connectionFactory);
                org.eclipse.jetty.io.Connection newConnection = connectionFactory.newConnection(getEndPoint(), context);
                getEndPoint().upgrade(newConnection);
                if (LOG.isDebugEnabled())
                    LOG.debug("SOCKS4 tunnel established: {} over {}", this, newConnection);
            }
            catch (Throwable x)
            {
                failed(x);
            }
        }
