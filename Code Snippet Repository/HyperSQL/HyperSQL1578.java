    public synchronized int getEncodedSize(int size) {

        try {
            return outCipher.getOutputSize(size);
        } catch (IllegalStateException ex) {
            try {
                outCipher.init(Cipher.ENCRYPT_MODE, key);

                return outCipher.getOutputSize(size);
            } catch (java.security.InvalidKeyException e) {
                throw Error.error(ErrorCode.X_S0531, e);
            }
        }
    }
