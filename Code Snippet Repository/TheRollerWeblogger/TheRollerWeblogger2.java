    public String getPlanetURL(String planet) {
        
        if(planet == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(WebloggerRuntimeConfig.getProperty("site.absoluteurl"));
        url.append("/").append(planet).append("/");
        
        return url.toString();
    }
