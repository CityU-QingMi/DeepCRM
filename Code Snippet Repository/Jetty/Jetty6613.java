        @Override
        protected void onFailure(Throwable x)
        {
            notifyError(x);

            if (ioState.wasAbnormalClose())
            {
                LOG.ignore(x);
                return;
            }

            if (LOG.isDebugEnabled())
                LOG.debug("Write flush failure",x);
            ioState.onWriteFailure(x);
        }
