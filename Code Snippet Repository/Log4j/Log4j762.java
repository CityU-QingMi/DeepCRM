    public void send(final byte[] msg) throws ExecutionException, InterruptedException, TimeoutException {
        if (producer != null) {
            final ProducerRecord<byte[], byte[]> newRecord = new ProducerRecord<>(topic, msg);
            if (syncSend) {
                final Future<RecordMetadata> response = producer.send(newRecord);
                response.get(timeoutMillis, TimeUnit.MILLISECONDS);
            } else {
                producer.send(newRecord, new Callback() {
                    @Override
                    public void onCompletion(final RecordMetadata metadata, final Exception e) {
                        if (e != null) {
                            LOGGER.error("Unable to write to Kafka in appender [" + getName() + "]", e);
                        }
                    }
                });
            }
        }
    }
