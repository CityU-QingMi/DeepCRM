    public synchronized void clear() {
        
        this.cache.clear();
        
        // clear metrics
        hits = 0;
        misses = 0;
        puts = 0;
        removes = 0;
        startTime = new Date();
    }
