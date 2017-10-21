    public CachedContent(int size) {
        
        // construct output stream
        if(size > 0) {
            this.outstream = new ByteArrayOutputStream(size);
        } else {
            this.outstream = new ByteArrayOutputStream(RollerConstants.EIGHT_KB_IN_BYTES);
        }
        
        // construct writer from output stream
        try {
            this.cachedWriter =
                    new PrintWriter(new OutputStreamWriter(this.outstream, "UTF-8"));
        } catch(UnsupportedEncodingException e) {
            // shouldn't be possible, java always supports utf-8
            throw new RuntimeException("Encoding problem", e);
        }
    }
