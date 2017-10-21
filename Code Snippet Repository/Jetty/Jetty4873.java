        public void run()
        {
            try {
                if (in!=null)
                    copy(in,out,-1);
                else
                    copy(read,write,-1);
            }
            catch(IOException e)
            {
                LOG.ignore(e);
                try{
                    if (out!=null)
                        out.close();
                    if (write!=null)
                        write.close();
                }
                catch(IOException e2)
                {
                    LOG.ignore(e2);
                }
            }
        }
