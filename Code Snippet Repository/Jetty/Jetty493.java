        @Override
        public void onFillable()
        {
            try
            {
                ByteBuffer buffer = BufferUtil.allocate(4);
                int filled = getEndPoint().fill(buffer);
                Assert.assertEquals(4, filled);
                Assert.assertArrayEquals(CAFE_BABE, buffer.array());

                // We are good, upgrade the connection
                getEndPoint().upgrade(connectionFactory.newConnection(getEndPoint(), context));
            }
            catch (Throwable x)
            {
                close();
                @SuppressWarnings("unchecked")
                Promise<Connection> promise = (Promise<Connection>)context.get(HttpClientTransport.HTTP_CONNECTION_PROMISE_CONTEXT_KEY);
                promise.failed(x);
            }
        }
