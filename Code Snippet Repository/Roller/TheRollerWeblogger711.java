    public String toString() {
        StringBuilder buf = new StringBuilder();
        
        buf.append("startDate = ").append(getStartDate()).append("\n");
        buf.append("endDate = ").append(getEndDate()).append("\n");
        buf.append("status = ").append(getStatus()).append("\n");
        buf.append("sortBy = ").append(getSortBy()).append("\n");
        buf.append("catName = ").append(getCategoryName()).append("\n");
        buf.append("tags = ").append(getTagsAsString()).append("\n");
        buf.append("text = ").append(getText()).append("\n");
        buf.append("page = ").append(getPage()).append("\n");
        
        return buf.toString();
    }
