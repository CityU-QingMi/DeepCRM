    public boolean before(TimeOfDay timeOfDay) {
        
        if(timeOfDay.hour > hour)
            return true;
        if(timeOfDay.hour < hour)
            return false;

        if(timeOfDay.minute > minute)
            return true;
        if(timeOfDay.minute < minute)
            return false;

        if(timeOfDay.second > second)
            return true;
        if(timeOfDay.second < second)
            return false;
        
        return false; // must be equal...
    }
