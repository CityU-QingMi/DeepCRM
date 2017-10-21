        @Override
        public void onFillInterestedFailed(Throwable cause)
        {
            Callback blocking = _blockingRead;
            if (blocking!=null)
            {
                _blockingRead=null;
                blocking.failed(cause);
                return;
            }
            super.onFillInterestedFailed(cause);
        }
