        public void awaitCount(int expectedCount) throws InterruptedException
        {
            long timeout = System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10);
            
            while (userCount.get() != expectedCount && (System.currentTimeMillis() < timeout))
            {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            
            assertThatCount(is(expectedCount));
        }
