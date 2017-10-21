    public String getPlanetGroupFeedURL(String planet, String group, String format) {
        
        if(planet == null || group == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(getPlanetGroupURL(planet, group, -1));
        url.append("feed/").append(format);
        
        return url.toString();
    }
