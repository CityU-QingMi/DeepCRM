    @Override
    public void send(final Event event)  {
        if (worker.isShutdown()) {
            throw new LoggingException("Unable to record event");
        }

        final Map<String, String> headers = event.getHeaders();
        final byte[] keyData = headers.get(FlumeEvent.GUID).getBytes(UTF8);
        try {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final DataOutputStream daos = new DataOutputStream(baos);
            daos.writeInt(event.getBody().length);
            daos.write(event.getBody(), 0, event.getBody().length);
            daos.writeInt(event.getHeaders().size());
            for (final Map.Entry<String, String> entry : headers.entrySet()) {
                daos.writeUTF(entry.getKey());
                daos.writeUTF(entry.getValue());
            }
            byte[] eventData = baos.toByteArray();
            if (secretKey != null) {
                final Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey);
                eventData = cipher.doFinal(eventData);
            }
            final Future<Integer> future = threadPool.submit(new BDBWriter(keyData, eventData, environment, database,
                gate, dbCount, getBatchSize(), lockTimeoutRetryCount));
            boolean interrupted = false;
            int ieCount = 0;
            do {
                try {
                    future.get();
                } catch (final InterruptedException ie) {
                    interrupted = true;
                    ++ieCount;
                }
            } while (interrupted && ieCount <= 1);

        } catch (final Exception ex) {
            throw new LoggingException("Exception occurred writing log event", ex);
        }
    }
