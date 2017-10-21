    public Trackback(WeblogEntry tEntry, String tURL)
            throws TrackbackNotAllowedException {
        
        // Make sure trackback to URL is allowed
        boolean allowTrackback = true;
        String allowedURLs = WebloggerConfig.getProperty("trackback.allowedURLs");
        if (!StringUtils.isEmpty(allowedURLs)) {
            // in the case that the administrator has enabled trackbacks
            // for only specific URLs, set it to false by default
            allowTrackback = false;
            String[] splitURLs = allowedURLs.split("\\|\\|");
            for (int i=0; i < splitURLs.length; i++) {
                Matcher m = Pattern.compile(splitURLs[i]).matcher(tURL);
                if (m.matches()) {
                    allowTrackback = true;
                    break;
                }
            }
        }
        
        if(!allowTrackback) {
            throw new TrackbackNotAllowedException(tURL);
        } else {
            // test url
            try {
                new URL(tURL);
            } catch(MalformedURLException ex) {
                // bad url
                throw new IllegalArgumentException("bad url: "+tURL);
            }
            
            entry = tEntry;
            trackbackURL = tURL;
        }
        
    }
