    public static void pop(String name) {
        if (!isActive()) {
            return;
        }

        ProfilingTimerBean currentTimer = current.get();

        //if the timers are matched up with each other (ie push("a"); pop("a"));
        if (currentTimer != null && name != null && name.equals(currentTimer.getResource())) {
            currentTimer.setEndTime();
            ProfilingTimerBean parent = currentTimer.getParent();
            //if we are the root timer, then print out the times
            if (parent == null) {
                printTimes(currentTimer);
                current.set(null); //for those servers that use thread pooling
            } else {
                current.set(parent);
            }
        } else {
            //if timers are not matched up, then print what we have, and then print warning.
            if (currentTimer != null) {
                printTimes(currentTimer);
                current.set(null); //prevent printing multiple times
                LOG.warn("Unmatched Timer. Was expecting {}, instead got {}", currentTimer.getResource(), name);
            }
        }
    }
