    public boolean equals(Object other) {

        if (other instanceof TimestampData) {
            return seconds == ((TimestampData) other).seconds
                   && nanos == ((TimestampData) other).nanos
                   && zone == ((TimestampData) other).zone;
        }

        return false;
    }
