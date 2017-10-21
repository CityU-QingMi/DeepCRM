    public static boolean isAbsoluteUrl(String url){
        if(url == null){
            return false;
        }
        
        int colonPos = url.indexOf(":");
        if(colonPos == -1){
            return false;
        }
        
        for(int i=0;i<colonPos;i++){
            if(VALID_SCHEME_CHAR.indexOf(url.charAt(i)) == -1){
                return false;
            }
        }
        
        return true;
    }
