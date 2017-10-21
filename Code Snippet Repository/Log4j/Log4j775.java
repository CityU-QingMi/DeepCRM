    @Override
    public boolean stop(final long timeout, final TimeUnit timeUnit) {
        setStopping();
        boolean stopped = true;
        for (final TriggeringPolicy triggeringPolicy : triggeringPolicies) {
            if (triggeringPolicy instanceof LifeCycle2) {
                stopped &= ((LifeCycle2) triggeringPolicy).stop(timeout, timeUnit);
            } else if (triggeringPolicy instanceof LifeCycle) {
                ((LifeCycle) triggeringPolicy).stop();
                stopped &= true;
            }
        }
        setStopped();
        return stopped;
    }
