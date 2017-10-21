        @Override
        public void run()
        {
            try
            {
                if (handle)
                    handleWithContext();
                else
                    getState().asyncError(failure);
                callback.succeeded();
            }
            catch (Throwable x)
            {
                callback.failed(x);
            }
        }
