        @Override
        public void onFillable()
        {
            try
            {
                while(_buffer.remaining()<_length)
                {
                    // Read data
                    int fill=getEndPoint().fill(_buffer);
                    if (fill<0)
                    {
                        getEndPoint().shutdownOutput();
                        return;
                    }
                    if (fill==0)
                    {
                        fillInterested();
                        return;
                    }
                } 
            }
            catch (Throwable x)
            {
                LOG.warn("PROXY error for "+getEndPoint(),x);
                close();
                return;
            }
            
            next();
        }
