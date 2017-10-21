        @Override
        public void onWritePossible() throws IOException
        {            
            // If we are able to write
            if(out.isReady())
            {   
                // Position our buffers limit to allow only buffersize bytes to be written
                int l=content.position()+buffersize;
                // respect the ultimate limit
                if (l>limit)
                    l=limit;
                content.limit(l);

                // if all content has been written
                if (!content.hasRemaining())
                {              
                    // complete the async lifecycle
                    async.complete();
                    return;
                }
                
                // write our limited buffer.  This will be an asynchronous write
                // and will always return immediately without blocking.  If a subsequent
                // call to out.isReady() returns false, then this onWritePossible method
                // will be called back when a write is possible.
                out.write(content);

                // Schedule a timer callback to pause writing.  Because isReady() is not called,
                // a onWritePossible callback is not scheduled.
                scheduler.schedule(this,pauseNS,TimeUnit.NANOSECONDS);
            }
        }
