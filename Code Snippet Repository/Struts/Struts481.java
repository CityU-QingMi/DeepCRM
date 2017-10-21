    public static void push(String name) {
        if (!isActive()) {
            return;
        }

        //create a new timer and start it
        ProfilingTimerBean newTimer = new ProfilingTimerBean(name);
        newTimer.setStartTime();

        //if there is a current timer - add the new timer as a child of it
        ProfilingTimerBean currentTimer = (ProfilingTimerBean) current.get();
        if (currentTimer != null) {
            currentTimer.addChild(newTimer);
        }

        //set the new timer to be the current timer
        current.set(newTimer);
    }
