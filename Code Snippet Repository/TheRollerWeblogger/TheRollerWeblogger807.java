    private synchronized void loadBannedIps() {
        
        if(bannedIpsFile != null) {
            
            try {
                HashSet newBannedIpList = new HashSet();
                
                // TODO: optimize this
                BufferedReader in = new BufferedReader(new FileReader(this.bannedIpsFile));
                
                String ip = null;
                while((ip = in.readLine()) != null) {
                    newBannedIpList.add(ip);
                }
                
                in.close();
                
                // list updated, reset modified file
                this.bannedIps = newBannedIpList;
                this.bannedIpsFile.clearChanged();
                
                log.info(this.bannedIps.size()+" banned ips loaded");
            } catch(Exception ex) {
               log.error("Error loading banned ips from file", ex);
            }
            
        }
    }
