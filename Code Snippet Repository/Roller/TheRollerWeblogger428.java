    public boolean equals(Object other) {
        
        if(this == other) {
            return true;
        }
        if( !(other instanceof WeblogHitCount) ) {
            return false;
        }
        
        // our natural key, or business key, is our weblog
        final WeblogHitCount that = (WeblogHitCount) other;
        return this.getWeblog().equals(that.getWeblog());
    }
