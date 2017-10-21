    @Test
    public void testEncodeText_JapaneseTextUtf8DoesntFitCharBuff_BytesDontFitByteBuff() throws Exception {
        final StringBuilderEncoder helper = new StringBuilderEncoder(StandardCharsets.UTF_8, 4, 8 * 1024);
        final StringBuilder text = new StringBuilder( // 日本語テスト文章
                "\u65e5\u672c\u8a9e\u30c6\u30b9\u30c8\u6587\u7ae0");
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(3, 50);
        helper.encode(text, destination);

        assertEquals("drained", 7, destination.drainPoints.size());
        destination.drain(destination.getByteBuffer());

        final byte[] utf8 = text.toString().getBytes(StandardCharsets.UTF_8);
        for (int i = 0; i < utf8.length; i++) {
            assertEquals("byte at " + i, utf8[i], destination.drained.get(i));
        }
    }
