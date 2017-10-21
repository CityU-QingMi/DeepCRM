    public void flush() {
        
        if(this.outstream == null) {
            throw new IllegalStateException("Cannot flush() after a close()!");
        }
        
        this.cachedWriter.flush();
        this.content = this.outstream.toByteArray();
        
        log.debug("FLUSHED "+this.content.length);
    }
