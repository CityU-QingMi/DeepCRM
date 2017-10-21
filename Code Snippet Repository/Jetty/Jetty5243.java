    public static KeyStore getKeyStore(Resource store, String storeType, String storeProvider, String storePassword) throws Exception
    {
        KeyStore keystore = null;

        if (store != null)
        {
            if (storeProvider != null)
            {
                keystore = KeyStore.getInstance(storeType, storeProvider);
            }
            else
            {
                keystore = KeyStore.getInstance(storeType);
            }
            
            if (!store.exists())
                throw new IllegalStateException("no valid keystore");
            
            try (InputStream inStream = store.getInputStream())
            {
                keystore.load(inStream, storePassword == null ? null : storePassword.toCharArray());
            }
        }
        
        return keystore;
    }
