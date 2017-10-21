        @Override
        public void writeFailed(Throwable x)
        {
            try
            {
                if (callback != null)
                {
                    callback.writeFailed(x);
                }
            }
            finally
            {
                onLocalClose();
            }
        }
