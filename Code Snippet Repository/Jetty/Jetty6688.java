    @Test
    public void testHash()
    {
        byte key[] = TypeUtil.fromHexString("00112233445566778899AABBCCDDEEFF");
        Assert.assertThat("Key size",key.length,is(16));

        // what the client sends
        String clientKey = String.valueOf(B64Code.encode(key));
        // what the server responds with
        String serverHash = AcceptHash.hashKey(clientKey);

        // how the client validates
        Assert.assertThat(serverHash,is("mVL6JKtNRC4tluIaFAW2hhMffgE="));
    }
