    public synchronized int encode(byte[] source, int sourceOffset,
                                   int length, byte[] dest, int destOffset) {

        if (outCipher == null) {
            return length;
        }

        try {
            outCipher.init(Cipher.ENCRYPT_MODE, key);

            return outCipher.doFinal(source, sourceOffset, length, dest,
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
