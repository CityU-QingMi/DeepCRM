    public String generateKey(PlanetRequest planetRequest) {
        
        StringBuilder key = new StringBuilder();
        
        key.append(CACHE_ID).append(":");
        key.append(planetRequest.getContext());
        key.append("/");
        key.append(planetRequest.getType());
        
        if(planetRequest.getFlavor() != null) {
            key.append("/").append(planetRequest.getFlavor());
        }
        
        // add language
        key.append("/").append(planetRequest.getLanguage());
        
        if(planetRequest.getFlavor() != null) {
            // add excerpts
            if(planetRequest.isExcerpts()) {
                key.append("/excerpts");
            }
        } else {
            // add login state
            if(planetRequest.getAuthenticUser() != null) {
                key.append("/user=").append(planetRequest.getAuthenticUser());
            }
        }
        
        // add group
        if (planetRequest.getGroup() != null) {
            key.append("/group=").append(planetRequest.getGroup());
        }

        return key.toString();
    }
