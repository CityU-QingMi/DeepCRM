    public static MediacastResource lookupResource(String url)
            throws MediacastException {
        
        if(url == null || url.trim().length() ==0) {
            return null;
        }
        
        MediacastResource resource = null;
        try {
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            int response = con.getResponseCode();
            String message = con.getResponseMessage();
            
            if(response != 200) {
                LOG.debug("Mediacast error " + response + ":" + message + " from url " + url);
                throw new MediacastException(BAD_RESPONSE, "weblogEdit.mediaCastResponseError");
            } else {
                String contentType = con.getContentType();
                long length = con.getContentLength();
                
                if(contentType == null || length == -1) {
                    LOG.debug("Response valid, but contentType or length is invalid");
                    throw new MediacastException(INCOMPLETE, "weblogEdit.mediaCastLacksContentTypeOrLength");
                }
                
                resource = new MediacastResource(url, contentType, length);
                LOG.debug("Valid mediacast resource = " + resource.toString());
                
            }
        } catch (MalformedURLException mfue) {
            LOG.debug("Malformed MediaCast url: " + url);
            throw new MediacastException(BAD_URL, "weblogEdit.mediaCastUrlMalformed", mfue);
        } catch (Exception e) {
            LOG.error("ERROR while checking MediaCast URL: " + url + ": " + e.getMessage());
            throw new MediacastException(CHECK_FAILED, "weblogEdit.mediaCastFailedFetchingInfo", e);
        }      
        return resource;
    }
