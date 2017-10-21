    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AId other = (AId) obj;
        if (other != null && id != other.id)
            return false;
        return true;
    }
