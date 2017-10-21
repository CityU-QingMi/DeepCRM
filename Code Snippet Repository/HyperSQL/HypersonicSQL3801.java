    public boolean equals(Object other) {

        if (other instanceof TimeData) {
            return seconds == ((TimeData) other).seconds
                   && nanos == ((TimeData) other).nanos
                   && zone ==  ((TimeData) other).zone ;
        }

        return false;
    }
