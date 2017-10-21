    public synchronized int decode(byte[] source, int sourceOffset,
                                   int length, byte[] dest, int destOffset) {

        if (inCipher == null) {
            return length;
        }

        try {
            inCipher.init(Cipher.DECRYPT_MODE, key);

            return inCipher.doFinal(source, sourceOffset, length, dest,
                                    destOffset);
        } catch (java.security.InvalidKeyException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (BadPaddingException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (IllegalBlockSizeException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (ShortBufferException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        }
    }
