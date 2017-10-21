        @Override
        public void run() {
            try {
                try (final Socket socket = serverSocket.accept()) {
                    if (socket != null) {
                        final InputStream is = socket.getInputStream();
                        while (!shutdown) {
                            final MappingIterator<LogEvent> mappingIterator = objectMapper.readerFor(Log4jLogEvent.class).readValues(is);
                            while (mappingIterator.hasNextValue()) {
                                queue.add(mappingIterator.nextValue());
                                ++count;
                            }
                        }
                    }
                }
            } catch (final EOFException eof) {
                // Socket is closed.
            } catch (final Exception e) {
                if (!shutdown) {
                    Throwables.rethrow(e);
                }
            }
        }
