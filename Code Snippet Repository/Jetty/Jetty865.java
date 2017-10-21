        private boolean notifyContent(ByteBuffer buffer)
        {
            try
            {
                return listener.onContent(request, FCGI.StreamType.STD_OUT, buffer);
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Exception while invoking listener " + listener, x);
                return false;
            }
        }
