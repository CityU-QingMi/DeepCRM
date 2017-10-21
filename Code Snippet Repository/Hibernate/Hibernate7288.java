    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        final User other = (User) obj;
        if (this.id == null || other.id == null) {
            return this == obj;
        }
        if(!this.id.equals(other.id)) {
            return this == obj;
        }
        return true;
    }
