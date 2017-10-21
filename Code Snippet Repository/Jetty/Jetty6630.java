        @Override
        public void onCompleteFailure(Throwable x)
        {
            for (FrameEntry entry : entries)
            {
                notifyCallbackFailure(entry.callback,x);
                entry.release();
            }
            entries.clear();
            failure = x;
            onFailure(x);
        }
