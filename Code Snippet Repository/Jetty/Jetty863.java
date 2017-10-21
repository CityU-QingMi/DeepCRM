        private void notifyHeaders()
        {
            try
            {
                listener.onHeaders(request);
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Exception while invoking listener " + listener, x);
            }
        }
