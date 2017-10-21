    public synchronized InputStream getInputStream(InputStream in) {

        if (inCipher == null) {
            return in;
        }

        try {
            inStreamCipher.init(Cipher.DECRYPT_MODE, key);

            return new CipherInputStream(in, inStreamCipher);
        } catch (java.security.InvalidKeyException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        }
    }
