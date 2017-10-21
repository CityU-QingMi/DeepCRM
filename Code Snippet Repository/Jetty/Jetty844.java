        @Override
        protected SendFailure send(HttpExchange exchange)
        {
            Request request = exchange.getRequest();
            normalizeRequest(request);

            // FCGI may be multiplexed, so create one channel for each request.
            int id = acquireRequest();
            HttpChannelOverFCGI channel = newHttpChannel(id, request);
            channels.put(id, channel);

            return send(channel, exchange);
        }
