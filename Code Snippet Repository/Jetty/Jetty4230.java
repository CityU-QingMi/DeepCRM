        @Override
        public void onWritePossible() throws IOException
        {
            // If we are able to write
            if(out.isReady())
            {
                // Allocated a copy buffer for each write, so as to not hold while paused
                // TODO put these buffers into a pool
                byte[] buffer = new byte[buffersize];
                
                // read some content into the copy buffer
                int len=content.read(buffer);
                
                // If we are at EOF
                if (len<0)
                {
                    // complete the async lifecycle
                    async.complete();
                    return;
                }
                
                // write out the copy buffer.  This will be an asynchronous write
                // and will always return immediately without blocking.  If a subsequent
                // call to out.isReady() returns false, then this onWritePossible method
                // will be called back when a write is possible.
                out.write(buffer,0,len);
                
                // Schedule a timer callback to pause writing.  Because isReady() is not called,
                // a onWritePossible callback is no scheduled.
                scheduler.schedule(this,pauseNS,TimeUnit.NANOSECONDS);
            }
        }
