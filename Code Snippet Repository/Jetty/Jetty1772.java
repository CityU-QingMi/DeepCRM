        @Override
        public synchronized void onFillable()
        {
            EndPoint endp = getEndPoint();
            try
            {
                boolean progress=true;
                while(progress)
                {
                    progress=false;

                    // Fill the input buffer with everything available
                    int filled=endp.fill(_in);
                    while (filled>0)
                    {
                        progress=true;
                        filled=endp.fill(_in);
                    }

                    // Write everything
                    int l=_in.remaining();
                    if (l>0)
                    {
                        FutureCallback blockingWrite= new FutureCallback();
                        endp.write(blockingWrite,_in);
                        blockingWrite.get();
                    }

                    // are we done?
                    if (endp.isInputShutdown())
                    {
                        endp.shutdownOutput();
                    }
                }
            }
            catch(InterruptedException|EofException e)
            {
                Log.getRootLogger().ignore(e);
            }
            catch(Exception e)
            {
                Log.getRootLogger().warn(e);
            }
            finally
            {
                if (endp.isOpen())
                    fillInterested();
            }
        }
