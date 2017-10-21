    public synchronized OutputStream getOutputStream(OutputStream out) {

        if (outCipher == null) {
            return out;
        }

        try {
            outStreamCipher.init(Cipher.ENCRYPT_MODE, key);

            return new CipherOutputStream(out, outStreamCipher);
        } catch (java.security.InvalidKeyException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        }
    }
