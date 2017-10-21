        @Override
        @SuppressWarnings("")
        public void succeeded()
        {
            recycle();
            for (int i = 0; i < callbacks.size(); ++i)
            {
                Callback callback = callbacks.get(i);
                if (callback != null)
                    callback.succeeded();
            }
        }
