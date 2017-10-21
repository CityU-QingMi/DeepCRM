    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof EmailAddress)) {
            return false;
        }
        final EmailAddress other = (EmailAddress) obj;
        if (this.email == null || other.email == null) {
            return this == obj;
        }
        if(!this.email.equals(other.email)) {
            return this == obj;
        }
        return true;
    }
