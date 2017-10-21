    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        buf.append(getId());
        buf.append(", ").append(getName());
        buf.append(", ").append(getTimeAcquired());
        buf.append(", ").append(getTimeLeased());
        buf.append("}");
        return buf.toString();
    }
