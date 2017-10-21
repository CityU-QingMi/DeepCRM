    public String getPlanetGroupURL(String planet, String group, int pageNum) {

        StringBuilder url = new StringBuilder();
        String sep = "?";
        
        url.append(getPlanetURL(planet));
        if (group != null) {
            url.append(sep);
            url.append("group=").append(group);
            sep = "&";
        }
        
        if (pageNum > 0) {
            url.append(sep);
            url.append("page=");
            url.append(pageNum);
        }
        
        return url.toString();
    }
