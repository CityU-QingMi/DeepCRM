    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        buf.append(getId());
        buf.append(", ").append(getName());
        buf.append(", ").append(getTotal());
        buf.append(", ").append(getLastUsed());
        buf.append("}");
        return buf.toString();
    }
