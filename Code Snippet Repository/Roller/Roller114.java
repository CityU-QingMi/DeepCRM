    public String getMediaFileURL(
            Weblog weblog,
            String fileAnchor,
            boolean absolute) {
        
        if(fileAnchor == null) {
            return null;
        }
        
        StringBuilder url = new StringBuilder();
        url.append(getWeblogURL(weblog, null, absolute));
        url.append("mediaresource");
        url.append("/");
        url.append(URLUtilities.encode(fileAnchor));
        
        return url.toString();
    }
