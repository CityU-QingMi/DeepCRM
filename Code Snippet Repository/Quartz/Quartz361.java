        @Override
        public void run() {
            while (!shutdown) {

                if (!shutdown) {
                    long timeToSleep = getClusterCheckinInterval();
                    long transpiredTime = (System.currentTimeMillis() - lastCheckin);
                    timeToSleep = timeToSleep - transpiredTime;
                    if (timeToSleep <= 0) {
                        timeToSleep = 100L;
                    }

                    if(numFails > 0) {
                        timeToSleep = Math.max(getDbRetryInterval(), timeToSleep);
                    }
                    
                    try {
                        Thread.sleep(timeToSleep);
                    } catch (Exception ignore) {
                    }
                }

                if (!shutdown && this.manage()) {
                    signalSchedulingChangeImmediately(0L);
                }

            }//while !shutdown
        }
