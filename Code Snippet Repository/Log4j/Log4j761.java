    private void closeProducer(final long timeout, final TimeUnit timeUnit) {
        if (producer != null) {
            // This thread is a workaround for this Kafka issue: https://issues.apache.org/jira/browse/KAFKA-1660
           final Thread closeThread = new Log4jThread(new Runnable() {
                @Override
                public void run() {
                    if (producer != null) {
                        producer.close();
                    }
                }
            }, "KafkaManager-CloseThread");
            closeThread.setDaemon(true); // avoid blocking JVM shutdown
            closeThread.start();
            try {
                closeThread.join(timeUnit.toMillis(timeout));
            } catch (final InterruptedException ignore) {
                Thread.currentThread().interrupt();
                // ignore
            }
        }
    }
