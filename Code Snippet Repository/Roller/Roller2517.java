    public void close() throws IOException {
        
        if(this.cachedWriter != null) {
            this.cachedWriter.flush();
            this.cachedWriter.close();
            this.cachedWriter = null;
        }
        
        if(this.outstream != null) {
            this.content = this.outstream.toByteArray();
            this.outstream.close();
            this.outstream = null;
        }
        
        log.debug("CLOSED");
    }
