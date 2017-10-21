    private StringBuilder getStringBuilder() {
        StringBuilder result = threadLocalStringBuilder.get();
        if (result == null) {
            result = new StringBuilder();
            threadLocalStringBuilder.set(result);
        }
        StringBuilders.trimToMaxSize(result, MAX_BUFFER_SIZE);
        result.setLength(0);
        return result;
    }
