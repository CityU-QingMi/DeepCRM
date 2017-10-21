    public List<Subscription> getSubscriptions() {
        
        List<Subscription> subs = Collections.emptyList();
        if(getGroup() != null) {
            Set<Subscription> subsSet = getGroup().getSubscriptions();
            
            // iterate over list and build display list
            subs = new ArrayList<Subscription>();
            for (Subscription sub : subsSet) {
                // only include external subs for display
                if(!sub.getFeedURL().startsWith("weblogger:")) {
                    subs.add(sub);
                }
            }
        }
        
        return subs;
    }
