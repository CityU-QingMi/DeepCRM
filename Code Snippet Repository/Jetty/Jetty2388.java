        @Override
        protected void onCompleteSuccess()
        {
            try
            {
                if (iterator instanceof Closeable)
                    ((Closeable)iterator).close();
                deferred.close();
            }
            catch (Throwable x)
            {
                _log.ignore(x);
            }
        }
