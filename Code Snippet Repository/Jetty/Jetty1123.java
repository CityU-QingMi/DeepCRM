    @Ignore
    @Test
    public void test() throws Exception
    {
//        HTTP2Client client = new HTTP2Client();
//        client.connect("localhost", 8080, new Promise.Adapter<Session>()
//        {
//            @Override
//            public void succeeded(Session session)
//            {
//                session.newStream(new HeadersFrame(info, null, true), new Stream.Listener.Adapter()
//                {
//                    @Override
//                    public void onData(Stream stream, DataFrame frame)
//                    {
//                        System.out.println("received frame = " + frame);
//                    }
//                }, new Adapter<Stream>()
//                {
//                    @Override
//                    public void succeeded(Stream stream)
//                    {
//                        DataFrame frame = new DataFrame(stream.getId(), ByteBuffer.wrap("HELLO".getBytes(StandardCharsets.UTF_8)), true);
//                        stream.data(frame, new Callback.Adapter());
//                    }
//                });
//            }
//        });

        // KINDA CALLBACK HELL ABOVE.
        // BELOW USING COMPLETABLES:

//        client.connect("localhost", 8080).then(session -> session.newStream(...)).then(stream -> stream.data(...));
    }
