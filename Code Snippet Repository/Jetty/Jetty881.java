        @Override
        public void onHeaders(int request)
        {
            HttpChannelOverFCGI channel = channels.get(request);
            if (LOG.isDebugEnabled())
                LOG.debug("Request {} headers on {}", request, channel);
            if (channel != null)
            {
                channel.onRequest();
                channel.dispatch();
            }
        }
