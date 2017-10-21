    @Override
    public Object clone() {
        AbstractTrigger<?> copy;
        try {
            copy = (AbstractTrigger<?>) super.clone();

            // Shallow copy the jobDataMap.  Note that this means that if a user
            // modifies a value object in this map from the cloned Trigger
            // they will also be modifying this Trigger. 
            if (jobDataMap != null) {
                copy.jobDataMap = (JobDataMap)jobDataMap.clone();
            }

        } catch (CloneNotSupportedException ex) {
            throw new IncompatibleClassChangeError("Not Cloneable.");
        }
        return copy;
    }
