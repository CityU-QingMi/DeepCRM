    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof StatCount)) {
            return false;
        }
        StatCount o = (StatCount)other;
        return new EqualsBuilder()
            .append(getSubjectId(), o.getSubjectId()) 
            .append(getTypeKey(), o.getTypeKey()) 
            .isEquals();
    }
