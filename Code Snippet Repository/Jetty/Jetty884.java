        @Override
        public void onFailure(int request, Throwable failure)
        {
            HttpChannelOverFCGI channel = channels.remove(request);
            if (LOG.isDebugEnabled())
                LOG.debug("Request {} failure on {}: {}", request, channel, failure);
            if (channel != null)
            {
                channel.onBadMessage(400, failure.toString());
            }
        }
