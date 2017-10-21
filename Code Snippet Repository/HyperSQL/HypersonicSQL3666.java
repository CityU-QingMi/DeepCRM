    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (other instanceof DateTimeType) {
            return super.equals(other)
                   && ((DateTimeType) other).withTimeZone == withTimeZone;
        }

        return false;
    }
