        @Override
        public DirectWriteRolloverStrategy build() {
            int maxIndex = Integer.MAX_VALUE;
            if (maxFiles != null) {
                maxIndex = Integer.parseInt(maxFiles);
                if (maxIndex < 0) {
                    maxIndex = Integer.MAX_VALUE;
                } else if (maxIndex < 2) {
                    LOGGER.error("Maximum files too small. Limited to " + DEFAULT_MAX_FILES);
                    maxIndex = DEFAULT_MAX_FILES;
                }
            }
            final int compressionLevel = Integers.parseInt(compressionLevelStr, Deflater.DEFAULT_COMPRESSION);
            return new DirectWriteRolloverStrategy(maxIndex, compressionLevel, config.getStrSubstitutor(),
                    customActions, stopCustomActionsOnError, tempCompressedFilePattern);
        }
