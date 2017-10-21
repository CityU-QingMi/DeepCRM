    public void resetPassword(String newPassword) throws WebloggerException {
        
        String encrypt = WebloggerConfig.getProperty("passwds.encryption.enabled");
        String algorithm = WebloggerConfig.getProperty("passwds.encryption.algorithm");
        if (Boolean.valueOf(encrypt)) {
            setPassword(Utilities.encodePassword(newPassword, algorithm));
        } else {
            setPassword(newPassword);
        }
    }
