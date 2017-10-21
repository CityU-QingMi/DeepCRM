    private String digest(String nonce, String username,String password,String uri,String nc) throws Exception
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] ha1;
        // calc A1 digest
        md.update(username.getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update("TestRealm".getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update(password.getBytes(StandardCharsets.ISO_8859_1));
        ha1 = md.digest();
        // calc A2 digest
        md.reset();
        md.update("GET".getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update(uri.getBytes(StandardCharsets.ISO_8859_1));
        byte[] ha2 = md.digest();

        // calc digest
        // request-digest = <"> < KD ( H(A1), unq(nonce-value) ":"
        // nc-value ":" unq(cnonce-value) ":" unq(qop-value) ":" H(A2) )
        // <">
        // request-digest = <"> < KD ( H(A1), unq(nonce-value) ":" H(A2)
        // ) > <">

        md.update(TypeUtil.toString(ha1, 16).getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update(nonce.getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update(nc.getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update(CNONCE.getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update("auth".getBytes(StandardCharsets.ISO_8859_1));
        md.update((byte) ':');
        md.update(TypeUtil.toString(ha2, 16).getBytes(StandardCharsets.ISO_8859_1));
        byte[] digest = md.digest();

        // check digest
        return TypeUtil.toString(digest, 16);
    }
