        @Override
        public void onFillable()
        {
            try
            {
                ByteBuffer buffer = BufferUtil.allocate(4);
                int filled = getEndPoint().fill(buffer);
                Assert.assertEquals(4, filled);
                Assert.assertArrayEquals(CAFE_BABE, buffer.array());
                getEndPoint().write(this, buffer);
            }
            catch (Throwable x)
            {
                close();
            }
        }
