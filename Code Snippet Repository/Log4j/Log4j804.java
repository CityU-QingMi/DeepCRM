    public void setTriggeringPolicy(final TriggeringPolicy triggeringPolicy) {
        triggeringPolicy.initialize(this);
        final TriggeringPolicy policy = this.triggeringPolicy;
        int count = 0;
        boolean policyUpdated = false;
        do {
            ++count;
        } while (!(policyUpdated = triggeringPolicyUpdater.compareAndSet(this, this.triggeringPolicy, triggeringPolicy))
                && count < MAX_TRIES);
        if (policyUpdated) {
            if (triggeringPolicy instanceof LifeCycle) {
                ((LifeCycle) triggeringPolicy).start();
            }
            if (policy instanceof LifeCycle) {
                ((LifeCycle) policy).stop();
            }
        } else {
            if (triggeringPolicy instanceof LifeCycle) {
                ((LifeCycle) triggeringPolicy).stop();
            }
        }
    }
