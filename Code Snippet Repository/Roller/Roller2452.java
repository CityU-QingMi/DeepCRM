    public boolean isBanned(String ip) {
        
        // update the banned ips list if needed
        this.loadBannedIpsIfNeeded(false);
        
        if(ip != null) {
            return this.bannedIps.contains(ip);
        } else {
            return false;
        }
    }
