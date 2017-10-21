    private int parseVersionString(String vstring) {        
        int myversion = 0;
        
        // NOTE: this assumes a maximum of 3 digits for the version number
        // so if we get to 10.0 then we'll need to upgrade this
        
        // strip out non-digits
        vstring = vstring.replaceAll("\\Q.\\E", "");
        vstring = vstring.replaceAll("\\D", "");
        if(vstring.length() > 3) {
            vstring = vstring.substring(0, 3);
        }
        
        // parse to an int
        try {
            int parsed = Integer.parseInt(vstring);            
            if(parsed < 100) {
                myversion = parsed * 10;
            } else {
                myversion = parsed;
            }
        } catch(Exception e) {}
        
        return myversion;
    }
