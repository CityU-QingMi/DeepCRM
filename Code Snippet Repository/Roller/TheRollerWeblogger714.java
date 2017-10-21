    public String toString() {
        StringBuilder buf = new StringBuilder();
        
        //title,locale,catId,tags,text,summary,dateString,status,comments,plugins
        buf.append("title = ").append(getTitle()).append("\n");
        buf.append("locale = ").append(getLocale()).append("\n");
        buf.append("status = ").append(getStatus()).append("\n");
        buf.append("catId = ").append(getCategoryId()).append("\n");
        buf.append("tags = ").append(getTagsAsString()).append("\n");
        buf.append("date = ").append(getDateString()).append("\n");
        buf.append("hours = ").append(getHours()).append("\n");
        buf.append("minutes = ").append(getMinutes()).append("\n");
        buf.append("seconds = ").append(getSeconds()).append("\n");
        buf.append("text = ").append(getText()).append("\n");
        buf.append("summary = ").append(getSummary()).append("\n");
        buf.append("search description = ").append(getSearchDescription()).append("\n");
        buf.append("comments = ").append(getAllowComments()).append("\n");
        buf.append("commentDays = ").append(getCommentDays()).append("\n");
        buf.append("plugins = ").append(Arrays.toString(getPlugins())).append("\n");
        
        return buf.toString();
    }
