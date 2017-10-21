    public String getPlanetGroupOpmlURL(String planet, String group) {
        
        if(planet == null || group == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(getPlanetGroupURL(planet, group, -1));
        url.append("opml");
        
        return url.toString();
    }
