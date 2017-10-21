    @Override
    public Date getFinalFireTime() {
        if (repeatCount == 0) {
            return startTime;
        }

        if (repeatCount == REPEAT_INDEFINITELY) {
            return (getEndTime() == null) ? null : getFireTimeBefore(getEndTime()); 
        }

        long lastTrigger = startTime.getTime() + (repeatCount * repeatInterval);

        if ((getEndTime() == null) || (lastTrigger < getEndTime().getTime())) { 
            return new Date(lastTrigger);
        } else {
            return getFireTimeBefore(getEndTime());
        }
    }
