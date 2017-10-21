        public default void onFailure(Session session, Throwable failure, Callback callback)
        {
            try
            {
                onFailure(session, failure);
                callback.succeeded();
            }
            catch (Throwable x)
            {
                callback.failed(x);
            }
        }
