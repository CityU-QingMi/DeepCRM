    public static byte[] getNewKey(String cipherName, String provider) {

        try {
            KeyGenerator generator = provider == null
                                     ? KeyGenerator.getInstance(cipherName)
                                     : KeyGenerator.getInstance(cipherName,
                                         provider);
            SecretKey key = generator.generateKey();
            byte[]    raw = key.getEncoded();

            return raw;
        } catch (java.security.NoSuchAlgorithmException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (NoSuchProviderException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        }
    }
