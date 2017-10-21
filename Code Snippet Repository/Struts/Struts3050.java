    private String getLocalizeMessage(final String message){
        if (SecurityUtil.isPackageProtectionEnabled()){
            return (String)AccessController.doPrivileged(new PrivilegedAction(){
                public Object run(){
                    return Localizer.getMessage(message); 
                }
            });
        } else {
            return Localizer.getMessage(message);
        }
    }
