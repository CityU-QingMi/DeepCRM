        private void notifyBegin(int code, String reason)
        {
            try
            {
                listener.onBegin(request, code, reason);
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Exception while invoking listener " + listener, x);
            }
        }
