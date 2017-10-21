    @Override
    public void handleEventException(final Throwable throwable, final long sequence,
            final AsyncLoggerConfigDisruptor.Log4jEventWrapper event) {
        final StringBuilder sb = new StringBuilder(512);
        sb.append("AsyncLogger error handling event seq=").append(sequence).append(", value='");
        try {
            sb.append(event);
        } catch (final Exception ignored) {
            sb.append("[ERROR calling ").append(event.getClass()).append(".toString(): ");
            sb.append(ignored).append("]");
        }
        sb.append("':");
        System.err.println(sb);
        throwable.printStackTrace();
    }
