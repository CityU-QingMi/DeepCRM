        @Override
        public org.eclipse.jetty.io.Connection newConnection(EndPoint endPoint, Map<String, Object> context) throws IOException
        {
            HttpDestination destination = (HttpDestination)context.get(HttpClientTransport.HTTP_DESTINATION_CONTEXT_KEY);
            SslContextFactory sslContextFactory = destination.getHttpClient().getSslContextFactory();
            if (destination.isSecure())
            {
                if (sslContextFactory != null)
                {
                    @SuppressWarnings("unchecked")
                    Promise<Connection> promise = (Promise<Connection>)context.get(HttpClientTransport.HTTP_CONNECTION_PROMISE_CONTEXT_KEY);
                    Promise<Connection> wrapped = promise;
                    if (promise instanceof Promise.Wrapper)
                        wrapped = ((Promise.Wrapper<Connection>)promise).unwrap();
                    if (wrapped instanceof TunnelPromise)
                    {
                        ((TunnelPromise)wrapped).setEndPoint(endPoint);
                        return connectionFactory.newConnection(endPoint, context);
                    }
                    else
                    {
                        // Replace the promise with the proxy promise that creates the tunnel to the server.
                        CreateTunnelPromise tunnelPromise = new CreateTunnelPromise(connectionFactory, endPoint, promise, context);
                        context.put(HttpClientTransport.HTTP_CONNECTION_PROMISE_CONTEXT_KEY, tunnelPromise);
                        return connectionFactory.newConnection(endPoint, context);
                    }
                }
                else
                {
                    throw new IOException("Cannot tunnel request, missing " +
                            SslContextFactory.class.getName() + " in " + HttpClient.class.getName());
                }
            }
            else
            {
                return connectionFactory.newConnection(endPoint, context);
            }
        }
