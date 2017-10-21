    protected void clean() {
        final Map<Path, IOException> failures = new HashMap<>();
        // Clean and gather failures
        for (final Path path : getPaths()) {
            if (Files.exists(path)) {
                for (int i = 0; i < getMaxTries(); i++) {
                    try {
                        if (clean(path, i)) {
                            if (failures.containsKey(path)) {
                                failures.remove(path);
                            }
                            break;
                        }
                    } catch (final IOException e) {
                        if (logger != null) {
                            logger.error("Caught exception cleaning {}", this, e);
                        }
                        // We will try again.
                        failures.put(path, e);
                    }
                    try {
                        Thread.sleep(SLEEP_RETRY_MILLIS);
                    } catch (final InterruptedException ignored) {
                        // ignore
                    }
                }
            }
        }
        // Fail on failures
        if (failures.size() > 0) {
            final StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (final Map.Entry<Path, IOException> failure : failures.entrySet()) {
                failure.getValue().printStackTrace();
                if (!first) {
                    sb.append(", ");
                }
                sb.append(failure.getKey()).append(" failed with ").append(failure.getValue());
                first = false;
            }
            Assert.fail(sb.toString());
        }

    }
