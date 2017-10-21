    public static MappedByteBuffer mmap(final FileChannel fileChannel, final String fileName, final long start,
            final int size) throws IOException {
        for (int i = 1;; i++) {
            try {
                LOGGER.debug("MMapAppender remapping {} start={}, size={}", fileName, start, size);

                final long startNanos = System.nanoTime();
                final MappedByteBuffer map = fileChannel.map(FileChannel.MapMode.READ_WRITE, start, size);
                map.order(ByteOrder.nativeOrder());

                final float millis = (float) ((System.nanoTime() - startNanos) / NANOS_PER_MILLISEC);
                LOGGER.debug("MMapAppender remapped {} OK in {} millis", fileName, millis);

                return map;
            } catch (final IOException e) {
                if (e.getMessage() == null || !e.getMessage().endsWith("user-mapped section open")) {
                    throw e;
                }
                LOGGER.debug("Remap attempt {}/{} failed. Retrying...", i, MAX_REMAP_COUNT, e);
                if (i < MAX_REMAP_COUNT) {
                    Thread.yield();
                } else {
                    try {
                        Thread.sleep(1);
                    } catch (final InterruptedException ignored) {
                        Thread.currentThread().interrupt();
                        throw e;
                    }
                }
            }
        }
    }
