    public static String calculate(Path path) throws NoSuchAlgorithmException, IOException
    {
        MessageDigest digest = MessageDigest.getInstance("SHA1");
        try (InputStream in = Files.newInputStream(path,StandardOpenOption.READ);
                NoOpOutputStream noop = new NoOpOutputStream();
                DigestOutputStream digester = new DigestOutputStream(noop,digest))
        {
            IO.copy(in,digester);
            return Hex.asHex(digest.digest());
        }
    }
