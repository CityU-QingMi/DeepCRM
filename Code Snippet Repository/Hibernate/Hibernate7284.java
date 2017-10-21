    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Contact)) {
            return false;
        }
        final Contact other = (Contact) obj;
        if (this.id == null || other.id == null) {
            return this == obj;
        }
        if(!this.id.equals(other.id)) {
            return this == obj;
        }
        return true;
    }
