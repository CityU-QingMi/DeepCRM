        @Override
        @SuppressWarnings("")
        public void failed(Throwable x)
        {
            recycle();
            for (int i = 0; i < callbacks.size(); ++i)
            {
                Callback callback = callbacks.get(i);
                if (callback != null)
                    callback.failed(x);
            }
        }
