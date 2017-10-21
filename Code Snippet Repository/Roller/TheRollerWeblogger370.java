    public Date getLeaseExpiration() {
        
        Date leaseAcquisitionTime = new Date(0);
        if(getTimeAcquired() != null) {
            leaseAcquisitionTime = getTimeAcquired();
        }
        
        // calculate lease expiration time
        Calendar cal = Calendar.getInstance();
        cal.setTime(leaseAcquisitionTime);
        cal.add(Calendar.MINUTE, getTimeLeased());
        
        return cal.getTime();
    }
