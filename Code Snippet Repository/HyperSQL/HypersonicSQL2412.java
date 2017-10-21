    public Crypto(String keyString, String cipherName, String provider) {

        try {
            byte[] encodedKey =
                StringConverter.hexStringToByteArray(keyString);

            key       = new SecretKeySpec(encodedKey, cipherName);
            outCipher = provider == null ? Cipher.getInstance(cipherName)
                                         : Cipher.getInstance(cipherName,
                                         provider);

            outCipher.init(Cipher.ENCRYPT_MODE, key);

            outStreamCipher = provider == null ? Cipher.getInstance(cipherName)
                                         : Cipher.getInstance(cipherName,
                                         provider);

            outStreamCipher.init(Cipher.ENCRYPT_MODE, key);

            inCipher = provider == null ? Cipher.getInstance(cipherName)
                                        : Cipher.getInstance(cipherName,
                                        provider);

            inCipher.init(Cipher.DECRYPT_MODE, key);

            inStreamCipher = provider == null ? Cipher.getInstance(cipherName)
                                        : Cipher.getInstance(cipherName,
                                        provider);

            inStreamCipher.init(Cipher.DECRYPT_MODE, key);

            return;
        } catch (NoSuchPaddingException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (NoSuchAlgorithmException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (InvalidKeyException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (NoSuchProviderException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        } catch (IOException e) {
            throw Error.error(ErrorCode.X_S0531, e);
        }
    }
