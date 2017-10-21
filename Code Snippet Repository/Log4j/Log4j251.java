    private static int calculateRingBufferSize(final String propertyName) {
        final String userPreferredRBSize = PropertiesUtil.getProperties().getStringProperty(propertyName,
                String.valueOf(RINGBUFFER_MIN_SIZE));
        try {
            int size = Integer.parseInt(userPreferredRBSize);
            if (size < RINGBUFFER_MIN_SIZE) {
                size = RINGBUFFER_MIN_SIZE;
                LOGGER.warn("Invalid {} {}, using minimum size {}.", propertyName, userPreferredRBSize,
                        RINGBUFFER_MIN_SIZE);
            }
            return ceilingNextPowerOfTwo(size);
        } catch (final Exception ex) {
            LOGGER.warn("Invalid {} {}, using default size {}.", propertyName, userPreferredRBSize,
                    RINGBUFFER_MIN_SIZE);
            return RINGBUFFER_MIN_SIZE;
        }
    }
