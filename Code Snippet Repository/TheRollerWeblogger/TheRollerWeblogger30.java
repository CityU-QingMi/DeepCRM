    public String toString() {
        StringBuilder buf = new StringBuilder();

        buf.append("{");
        buf.append(getFeedURL()).append(", ");
        buf.append(getSiteURL()).append(", ");
        buf.append(getTitle()).append(", ");
        buf.append(getAuthor()).append(", ");
        buf.append(getLastUpdated());
        buf.append("}");
        
        return buf.toString();
    }
