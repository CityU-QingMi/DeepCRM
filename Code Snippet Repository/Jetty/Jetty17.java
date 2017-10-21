        private void handleAsyncIO() throws IOException
        {
            // This method is called:
            //   1) after first registering a WriteListener (ready for first write)
            //   2) after first registering a ReadListener iff write is ready
            //   3) when a previous write completes after an output.isReady() returns false
            //   4) from an input callback 
           
            // We should try to read, only if we are able to write!
            while (true)
            {
                if (!output.isReady())
                    // Don't even try to read anything until it is possible to write something,
                    // when onWritePossible will be called
                    break;

                if (!input.isReady())
                    // Nothing available to read, so wait for another call to onDataAvailable
                    break;
                
                int read = input.read(buffer);
                if (read<0)
                {
                    if (complete.compareAndSet(false,true))
                        asyncContext.complete();
                    break;
                }
                else if (read>0)
                {
                    output.write(buffer, 0, read);
                }
            }
        }
