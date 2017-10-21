        private CountDownLatch getLatch()
        {
            synchronized (this)
            {
                if (latch == null)
                {
                    latch = new CountDownLatch(count);
                }
            }

            return latch;
        }
