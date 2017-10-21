        @Override
        public void onAllDataRead() throws IOException
        {
            if (!provider.isClosed())
            {
                process(BufferUtil.EMPTY_BUFFER, new Callback()
                {
                    @Override
                    public void failed(Throwable x)
                    {
                        onError(x);
                    }
                }, true);
            }

            if (_log.isDebugEnabled())
                _log.debug("{} proxying content to upstream completed", getRequestId(clientRequest));
        }
