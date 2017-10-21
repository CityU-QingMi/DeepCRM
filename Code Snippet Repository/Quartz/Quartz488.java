    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Trigger))
            return false;
        
        Trigger other = (Trigger)o;

        return !(other.getKey() == null || getKey() == null) && getKey().equals(other.getKey());

    }
