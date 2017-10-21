        private ByteBuffer gzip(List<ByteBuffer> buffers, boolean finished) throws IOException
        {
            for (ByteBuffer buffer : buffers)
                write(gzipOut, buffer);
            if (finished)
                gzipOut.close();
            byte[] gzipBytes = out.toByteArray();
            out.reset();
            return ByteBuffer.wrap(gzipBytes);
        }
