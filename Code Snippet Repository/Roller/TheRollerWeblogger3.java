    public String getPlanetGroupURL(String planet, String group, int pageNum) {
        
        if(planet == null || group == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        
        url.append(getPlanetURL(planet));
        url.append("group/").append(group).append("/");
        
        if(pageNum > 0) {
            url.append("?page=");
            url.append(pageNum);
        }
        
        return url.toString();
    }
