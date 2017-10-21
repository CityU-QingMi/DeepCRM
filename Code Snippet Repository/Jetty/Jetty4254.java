        @Override
        public void close()
        {
            synchronized (this)
            {
                closed = true;
                heartBeat.cancel(false);
            }
            async.complete();
        }
