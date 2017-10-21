    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ConfigurationScheduler [name=");
        sb.append(name);
        sb.append(", [");
        if (executorService != null) {
            final Queue<Runnable> queue = ((ScheduledThreadPoolExecutor) executorService).getQueue();
            boolean first = true;
            for (final Runnable runnable : queue) {
                if (!first) {
                    sb.append(", ");
                }
                sb.append(runnable.toString());
                first = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }
