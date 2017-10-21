        private void tunnel(HttpDestination destination, Connection connection)
        {
            String target = destination.getOrigin().getAddress().asString();
            Origin.Address proxyAddress = destination.getConnectAddress();
            HttpClient httpClient = destination.getHttpClient();
            long connectTimeout = httpClient.getConnectTimeout();
            Request connect = httpClient.newRequest(proxyAddress.getHost(), proxyAddress.getPort())
                    .method(HttpMethod.CONNECT)
                    .path(target)
                    .header(HttpHeader.HOST, target)
                    .idleTimeout(2 * connectTimeout, TimeUnit.MILLISECONDS)
                    .timeout(connectTimeout, TimeUnit.MILLISECONDS);
            ProxyConfiguration.Proxy proxy = destination.getProxy();
            if (proxy != null && proxy.isSecure())
                connect.scheme(HttpScheme.HTTPS.asString());

            final HttpConversation conversation = ((HttpRequest)connect).getConversation();
            conversation.setAttribute(EndPoint.class.getName(), endPoint);

            connect.attribute(Connection.class.getName(), new ProxyConnection(destination, connection, promise));

            connection.send(connect, result ->
            {
                // The EndPoint may have changed during the conversation, get the latest.
                EndPoint endPoint = (EndPoint)conversation.getAttribute(EndPoint.class.getName());
                if (result.isSucceeded())
                {
                    Response response = result.getResponse();
                    if (response.getStatus() == HttpStatus.OK_200)
                    {
                        tunnelSucceeded(endPoint);
                    }
                    else
                    {
                        HttpResponseException failure = new HttpResponseException("Unexpected " + response +
                                " for " + result.getRequest(), response);
                        tunnelFailed(endPoint, failure);
                    }
                }
                else
                {
                    tunnelFailed(endPoint, result.getFailure());
                }
            });
        }
