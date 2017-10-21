    private void convertThrowable(final StringBuilder builder, final Throwable throwable) {
        builder.append(throwable.toString()).append('\n');
        for (final StackTraceElement element : throwable.getStackTrace()) {
            builder.append("\tat ").append(element).append('\n');
        }
        if (throwable.getCause() != null) {
            builder.append("Caused by ");
            this.convertThrowable(builder, throwable.getCause());
        }
    }
