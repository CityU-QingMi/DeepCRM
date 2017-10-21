        @Override
        public Void call() throws Exception
        {
            URL url = null;
            try
            {
                String addr = _tester.createConnector(true);
                for (int i = 0; i < NUM_LOOPS; i++)
                {
                    url = new URL(addr + "/context/test?priority=" + (_num % QoSFilter.__DEFAULT_MAX_PRIORITY) + "&n=" + _num + "&l=" + i);
                    url.getContent();
                }
            }
            catch (Exception e)
            {
                LOG.debug("Request " + url + " failed", e);
            }

            return null;
        }
