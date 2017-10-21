    public Map<String, Object> getStats() {
        
        Map<String, Object> stats = new HashMap<String, Object>();
        stats.put("startTime", this.startTime);
        stats.put("hits", this.hits);
        stats.put("misses", this.misses);
        stats.put("puts", this.puts);
        stats.put("removes", this.removes);
        
        // calculate efficiency
        if((misses - removes) > 0) {
            double efficiency = hits / (misses + hits);
            stats.put("efficiency", efficiency * RollerConstants.PERCENT_100);
        }
        
        return stats;
    }
