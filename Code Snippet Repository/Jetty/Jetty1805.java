    public static String convertCredentialLdapToJetty(String encryptedPassword)
    {
        if (encryptedPassword == null)
        {
            return null;
        }

        if (encryptedPassword.toUpperCase(Locale.ENGLISH).startsWith("{MD5}"))
        {
            String src = encryptedPassword.substring("{MD5}".length(), encryptedPassword.length());
            return "MD5:" + base64ToHex(src);
        }

        if (encryptedPassword.toUpperCase(Locale.ENGLISH).startsWith("{CRYPT}"))
        {
            return "CRYPT:" + encryptedPassword.substring("{CRYPT}".length(), encryptedPassword.length());
        }

        return encryptedPassword;
    }
