    public void addBannedIp(String ip) {
        
        if(ip == null) {
            return;
        }
        
        // update the banned ips list if needed
        this.loadBannedIpsIfNeeded(false);
        
        if(!this.bannedIps.contains(ip) && 
                (bannedIpsFile != null && bannedIpsFile.canWrite())) {
            
            try {
                synchronized(this) {
                    // add to file
                    PrintWriter out = new PrintWriter(new FileWriter(this.bannedIpsFile, true));
                    out.println(ip);
                    out.close();
                    this.bannedIpsFile.clearChanged();
                    
                    // add to Set
                    this.bannedIps.add(ip);
                }
                
                log.debug("ADDED "+ip);
            } catch(Exception e) {
                log.error("Error adding banned ip to file", e);
            }
        }
    }
