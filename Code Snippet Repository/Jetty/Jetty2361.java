        protected void complete()
        {
            buffer = null;
            offset = 0;
            length = 0;
            Callback c = callback;
            callback = null;
            state = WriteState.IDLE;
            // Call the callback only after the whole state has been reset,
            // because the callback may trigger a reentrant call and
            // the state must already be the new one that we reset here.
            c.succeeded();
        }
