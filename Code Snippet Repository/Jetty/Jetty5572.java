        public void awaitFinish(PathWatcher pathWatcher) throws IOException, InterruptedException
        {
            //assertThat("Trigger Path must be set",triggerPath,notNullValue());
            //assertThat("Trigger Type must be set",triggerType,notNullValue());
            double multiplier = 25.0;
            long awaitMillis = (long)((double)pathWatcher.getUpdateQuietTimeMillis() * multiplier);
            LOG.debug("Waiting for finish ({} ms)",awaitMillis);
            assertThat("Timed Out (" + awaitMillis + "ms) waiting for capture to finish",finishedLatch.await(awaitMillis,TimeUnit.MILLISECONDS),is(true));
            LOG.debug("Finished capture");
        }
