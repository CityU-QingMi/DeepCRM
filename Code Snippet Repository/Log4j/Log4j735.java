    @Override
    public String convertToDatabaseColumn(final Throwable throwable) {
        if (throwable == null) {
            return null;
        }

        final StringBuilder builder = new StringBuilder();
        this.convertThrowable(builder, throwable);
        return builder.toString();
    }
