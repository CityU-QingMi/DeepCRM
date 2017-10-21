    public static synchronized String generateDigest(
            byte[] nonce, byte[] created, byte[] password) {
        String result = null;
        try {
            MessageDigest digester = MessageDigest.getInstance("SHA");
            digester.reset();
            digester.update(nonce);
            digester.update(created);
            digester.update(password);
            byte[] digest = digester.digest();
            result = base64Encode(digest);
        }
        catch (NoSuchAlgorithmException e) {
            result = null;
        }
        return result;
    }
