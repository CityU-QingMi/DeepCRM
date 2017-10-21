    @Test
    public void testEncodeText_JapaneseTextShiftJisDoesntFitCharBuff_DoesntFitTempByteBuff_BytesDontFitDestinationByteBuff() throws Exception {
        final Charset SHIFT_JIS = Charset.forName("Shift_JIS");
        final StringBuilderEncoder helper = new StringBuilderEncoder(SHIFT_JIS, 4, 5);
        final StringBuilder text = new StringBuilder( // 日本語テスト文章日本語テスト文章
                "\u65e5\u672c\u8a9e\u30c6\u30b9\u30c8\u6587\u7ae0\u65e5\u672c\u8a9e\u30c6\u30b9\u30c8\u6587\u7ae0");
        final SpyByteBufferDestination destination = new SpyByteBufferDestination(3, 50);
        helper.encode(text, destination);

        destination.drain(destination.getByteBuffer());

        final byte[] bytes = text.toString().getBytes(SHIFT_JIS);
        for (int i = 0; i < bytes.length; i++) {
            assertEquals("byte at " + i, bytes[i], destination.drained.get(i));
        }
    }
