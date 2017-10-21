        @Override
        public DefaultRolloverStrategy build() {
            int minIndex;
            int maxIndex;
            boolean useMax;

            if (fileIndex != null && fileIndex.equalsIgnoreCase("nomax")) {
                minIndex = Integer.MIN_VALUE;
                maxIndex = Integer.MAX_VALUE;
                useMax = false;
            } else {
                useMax = fileIndex == null ? true : fileIndex.equalsIgnoreCase("max");
                minIndex = MIN_WINDOW_SIZE;
                if (min != null) {
                    minIndex = Integer.parseInt(min);
                    if (minIndex < 1) {
                        LOGGER.error("Minimum window size too small. Limited to " + MIN_WINDOW_SIZE);
                        minIndex = MIN_WINDOW_SIZE;
                    }
                }
                maxIndex = DEFAULT_WINDOW_SIZE;
                if (max != null) {
                    maxIndex = Integer.parseInt(max);
                    if (maxIndex < minIndex) {
                        maxIndex = minIndex < DEFAULT_WINDOW_SIZE ? DEFAULT_WINDOW_SIZE : minIndex;
                        LOGGER.error("Maximum window size must be greater than the minimum windows size. Set to " + maxIndex);
                    }
                }
            }
            final int compressionLevel = Integers.parseInt(compressionLevelStr, Deflater.DEFAULT_COMPRESSION);
            return new DefaultRolloverStrategy(minIndex, maxIndex, useMax, compressionLevel, config.getStrSubstitutor(),
                    customActions, stopCustomActionsOnError, tempCompressedFilePattern);
        }
