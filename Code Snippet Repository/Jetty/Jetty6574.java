        private void notifyCallbackFailure(WriteCallback callback, Throwable failure)
        {
            try
            {
                if (callback != null)
                    callback.writeFailed(failure);
            }
            catch (Throwable x)
            {
                LOG.debug("Exception while notifying failure of callback " + callback,x);
            }
        }
