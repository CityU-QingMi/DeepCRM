    private static int determineValidRegionLength(final String name, final int regionLength) {
        if (regionLength > MAX_REGION_LENGTH) {
            LOGGER.info("MemoryMappedAppender[{}] Reduced region length from {} to max length: {}", name, regionLength,
                    MAX_REGION_LENGTH);
            return MAX_REGION_LENGTH;
        }
        if (regionLength < MIN_REGION_LENGTH) {
            LOGGER.info("MemoryMappedAppender[{}] Expanded region length from {} to min length: {}", name, regionLength,
                    MIN_REGION_LENGTH);
            return MIN_REGION_LENGTH;
        }
        final int result = Integers.ceilingNextPowerOfTwo(regionLength);
        if (regionLength != result) {
            LOGGER.info("MemoryMappedAppender[{}] Rounded up region length from {} to next power of two: {}", name,
                    regionLength, result);
        }
        return result;
    }
