        protected void fail(Throwable failure)
        {
            try
            {
                listener.onFailure(request, failure);
            }
            catch (Throwable x)
            {
                if (LOG.isDebugEnabled())
                    LOG.debug("Exception while invoking listener " + listener, x);
            }
        }
