    public BufferedResponseHandler()
    {
        // include only GET requests
        
        _methods.include(HttpMethod.GET.asString());
        // Exclude images, aduio and video from buffering
        for (String type:MimeTypes.getKnownMimeTypes())
        {
            if (type.startsWith("image/")||
                type.startsWith("audio/")||
                type.startsWith("video/"))
                _mimeTypes.exclude(type);
        }
        LOG.debug("{} mime types {}",this,_mimeTypes);
    }
