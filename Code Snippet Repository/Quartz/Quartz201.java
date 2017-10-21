    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof JobDetail)) {
            return false;
        }

        JobDetail other = (JobDetail) obj;

        if(other.getKey() == null || getKey() == null)
            return false;
        
        if (!other.getKey().equals(getKey())) {
            return false;
        }
            
        return true;
    }
