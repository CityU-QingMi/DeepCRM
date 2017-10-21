        @Override
        public InvocationType getInvocationType()
        {
            Callback callback;
            synchronized (this)
            {
                callback = this.callback;
            }
            return callback != null ? callback.getInvocationType() : Callback.super.getInvocationType();
        }
