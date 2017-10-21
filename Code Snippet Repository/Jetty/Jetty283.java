        private void tunnelSucceeded(EndPoint endPoint)
        {
            try
            {
                // Replace the promise back with the original
                context.put(HttpClientTransport.HTTP_CONNECTION_PROMISE_CONTEXT_KEY, promise);
                HttpDestination destination = (HttpDestination)context.get(HttpClientTransport.HTTP_DESTINATION_CONTEXT_KEY);
                HttpClient client = destination.getHttpClient();
                ClientConnectionFactory sslConnectionFactory = client.newSslClientConnectionFactory(connectionFactory);
                HttpConnectionOverHTTP oldConnection = (HttpConnectionOverHTTP)endPoint.getConnection();
                org.eclipse.jetty.io.Connection newConnection = sslConnectionFactory.newConnection(endPoint, context);
                // Creating the connection will link the new Connection the EndPoint,
                // but we need the old Connection linked for the upgrade to do its job.
                endPoint.setConnection(oldConnection);
                endPoint.upgrade(newConnection);
                if (LOG.isDebugEnabled())
                    LOG.debug("HTTP tunnel established: {} over {}", oldConnection, newConnection);
            }
            catch (Throwable x)
            {
                tunnelFailed(endPoint, x);
            }
        }
