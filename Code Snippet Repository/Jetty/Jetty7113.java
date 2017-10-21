    @Test
    @Slow
    public void testCase6_4_3() throws Exception
    {
        // Disable Long Stacks from Parser (we know this test will throw an exception)
        try (StacklessLogging scope = new StacklessLogging(Parser.class))
        {
            ByteBuffer payload = ByteBuffer.allocate(64);
            BufferUtil.clearToFill(payload);
            payload.put(TypeUtil.fromHexString("cebae1bdb9cf83cebcceb5")); // good
            payload.put(TypeUtil.fromHexString("f4908080")); // INVALID
            payload.put(TypeUtil.fromHexString("656469746564")); // good
            BufferUtil.flipToFlush(payload,0);

            List<WebSocketFrame> send = new ArrayList<>();
            send.add(new TextFrame().setPayload(payload));
            send.add(new CloseInfo(StatusCode.NORMAL).asFrame());

            List<WebSocketFrame> expect = new ArrayList<>();
            expect.add(new CloseInfo(StatusCode.BAD_PAYLOAD).asFrame());

            try (Fuzzer fuzzer = new Fuzzer(this))
            {
                fuzzer.connect();

                ByteBuffer net = fuzzer.asNetworkBuffer(send);

                int splits[] = { 17, 21, net.limit() };

                ByteBuffer part1 = net.slice(); // Header + good UTF
                part1.limit(splits[0]);
                ByteBuffer part2 = net.slice(); // invalid UTF
                part2.position(splits[0]);
                part2.limit(splits[1]);
                ByteBuffer part3 = net.slice(); // good UTF
                part3.position(splits[1]);
                part3.limit(splits[2]);

                fuzzer.send(part1); // the header + good utf
                TimeUnit.MILLISECONDS.sleep(500);
                fuzzer.send(part2); // the bad UTF
                TimeUnit.MILLISECONDS.sleep(500);
                fuzzer.send(part3); // the rest (shouldn't work)

                fuzzer.expect(expect);
            }
        }
    }
