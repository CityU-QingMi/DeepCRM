        public default void onClose(Session session, GoAwayFrame frame, Callback callback)
        {
            try
            {
                onClose(session, frame);
                callback.succeeded();
            }
            catch (Throwable x)
            {
                callback.failed(x);
            }
        }
