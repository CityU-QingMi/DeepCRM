        @Override 
        public void run()
        {
            try
            {
                // When the pause timer wakes up, call onWritePossible.  Either isReady() will return
                // true and another chunk of content will be written, or it will return false and the 
                // onWritePossible() callback will be scheduled when a write is next possible.
                onWritePossible();
            }
            catch(Exception e)
            {
                onError(e);
            }
        }
