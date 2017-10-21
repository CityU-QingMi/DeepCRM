    public boolean removeListener(String listenerName) {
        Iterator<TriggerListener> itr = listeners.iterator();
        while(itr.hasNext()) {
            TriggerListener l = itr.next();
            if(l.getName().equals(listenerName)) {
                itr.remove();
                return true;
            }
        }
        return false;
    }
