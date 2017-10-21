        private void release()
        {
            ByteBufferPool bufferPool = httpClient.getByteBufferPool();
            if (headerBuffer != BufferUtil.EMPTY_BUFFER)
                bufferPool.release(headerBuffer);
            headerBuffer = null;
            if (chunkBuffer != BufferUtil.EMPTY_BUFFER)
                bufferPool.release(chunkBuffer);
            chunkBuffer = null;
            contentBuffer = null;
        }
