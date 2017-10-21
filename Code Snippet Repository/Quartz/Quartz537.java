    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {

        Iterator<TriggerListener> itr = listeners.iterator();
        while(itr.hasNext()) {
            TriggerListener l = itr.next();
            if(l.vetoJobExecution(trigger, context)) {
                return true;
            }
        }
        return false;
    }
