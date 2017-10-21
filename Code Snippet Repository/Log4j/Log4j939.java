        @Override
        public void run() {
            try {
                final long millis = scheduledFuture.getFireTime().getTime() - System.currentTimeMillis();
                if (millis > 0) {
                    LOGGER.debug("{} Cron thread woke up {} millis early. Sleeping", name, millis);
                    try {
                        Thread.sleep(millis);
                    } catch (final InterruptedException ie) {
                        // Ignore the interruption.
                    }
                }
                runnable.run();
            } catch(final Throwable ex) {
                LOGGER.error("{} caught error running command", name, ex);
            } finally {
                final Date fireDate = cronExpression.getNextValidTimeAfter(new Date());
                final ScheduledFuture<?> future = schedule(this, nextFireInterval(fireDate), TimeUnit.MILLISECONDS);
                LOGGER.debug("{} Cron expression {} scheduled to fire again at {}", name, cronExpression.getCronExpression(),
                        fireDate);
                scheduledFuture.reset(future, fireDate);
            }
        }
