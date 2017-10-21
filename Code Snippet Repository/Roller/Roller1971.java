    @Override
    public boolean equals(Object other) {
        
        if(this == other) {
            return true;
        }
        if( !(other instanceof TaskLock) ) {
            return false;
        }
        // our natural key, or business key, is our name
        final TaskLock that = (TaskLock) other;
        return this.getName().equals(that.getName());
    }
