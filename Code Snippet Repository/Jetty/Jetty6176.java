        public void stop()
        {
            if (this.sconnection != null)
            {
                this.sconnection.stopEcho();
                try
                {
                    this.sconnection.close();
                }
                catch (IOException ignore)
                {
                    /* ignore */
                }
            }
        }
