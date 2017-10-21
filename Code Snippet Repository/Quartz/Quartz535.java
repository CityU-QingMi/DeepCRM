    public boolean removeListener(String listenerName) {
        Iterator<JobListener> itr = listeners.iterator();
        while(itr.hasNext()) {
            JobListener jl = itr.next();
            if(jl.getName().equals(listenerName)) {
                itr.remove();
                return true;
            }
        }
        return false;
    }
