        @Override
        public void onEnd(int request)
        {
            HttpChannelOverFCGI channel = channels.remove(request);
            if (LOG.isDebugEnabled())
                LOG.debug("Request {} end on {}", request, channel);
            if (channel != null)
            {
                channel.onContentComplete();
                channel.onRequestComplete();
            }
        }
