        @Override
        public boolean onContent(int request, FCGI.StreamType stream, ByteBuffer buffer)
        {
            HttpChannelOverFCGI channel = channels.get(request);
            if (LOG.isDebugEnabled())
                LOG.debug("Request {} {} content {} on {}", request, stream, buffer, channel);
            if (channel != null)
            {
                ByteBuffer copy = ByteBuffer.allocate(buffer.remaining());
                copy.put(buffer).flip();
                channel.onContent(new HttpInput.Content(copy));
            }
            return false;
        }
