        private void notifyHeader(HttpField httpField)
        {
            try
            {
                listener.onHeader(request, httpField);
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Exception while invoking listener " + listener, x);
            }
        }
