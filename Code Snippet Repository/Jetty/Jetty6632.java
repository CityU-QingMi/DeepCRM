        private void succeedEntries()
        {
            // Do not allocate the iterator here.
            for (int i = 0; i < entries.size(); ++i)
            {
                FrameEntry entry = entries.get(i);
                notifyCallbackSuccess(entry.callback);
                entry.release();
            }
            entries.clear();
        }
