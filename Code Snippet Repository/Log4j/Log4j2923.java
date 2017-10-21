        @Override
        public void run() {
            System.out.println("STARTING..................");

            while (!shutdown) {
                // Generate rand number between 1 to 10
                final int rand = ran.nextInt(9) + 1;

                // Sleep for rand seconds
                try {
                    Thread.sleep(rand * 1000);
                } catch (final InterruptedException e) {
                    logger.warn("WARN", e);
                }

                // Write rand number of logs
                for (int i = 0; i < rand; i++) {
                    final int eventIndex = (Math.abs(ran.nextInt())) % events.size();
                    final AuditEvent event = events.get(eventIndex);
                    RequestContext.setUserId(member);
                    event.logEvent();

                    if ((rand % 4) == 1) {
                        logger.debug("DEBUG level logging.....");
                    } else if ((rand % 4) == 2) {
                        logger.info("INFO level logging.....");
                    } else if ((rand % 4) == 3) {
                        logger.warn("WARN level logging.....");
                    } else {
                        logger.error("ERROR level logging.....");
                    }
                }

            }
        }
