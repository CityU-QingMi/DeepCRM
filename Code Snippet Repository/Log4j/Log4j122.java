    private StringBuilder getBuffer() {
        if (buffer == null) {
            buffer = new ThreadLocal<>();
        }
        StringBuilder result = buffer.get();
        if (result == null) {
            final int currentPatternLength = messagePattern == null ? 0 : messagePattern.length();
            result = new StringBuilder(Math.max(MIN_BUILDER_SIZE, currentPatternLength * 2));
            buffer.set(result);
        }
        result.setLength(0);
        return result;
    }
