        @Override
        public void run()
        {
            try
            {
                while (!latch.await(10,TimeUnit.SECONDS))
                {
                    System.err.println("Ping");
                    ByteBuffer data = ByteBuffer.allocate(3);
                    data.put(new byte[]
                    { (byte)1, (byte)2, (byte)3 });
                    data.flip();
                    session.getRemote().sendPing(data);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
