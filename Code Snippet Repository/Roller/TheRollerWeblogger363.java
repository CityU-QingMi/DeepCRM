    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        buf.append(getId());
        buf.append(", ").append(getName());
        buf.append(", ").append(getPingUrl());
        buf.append(", ").append(getLastSuccess());
        buf.append(", ").append(isAutoEnabled());
        buf.append("}");
        return buf.toString();
    }
