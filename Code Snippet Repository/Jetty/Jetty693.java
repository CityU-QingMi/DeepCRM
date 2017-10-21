        private TLSRecord read(TLSRecord.Type type, InputStream input, byte[] bytes, int offset, int length) throws IOException
        {
            while (length > 0)
            {
                int read = input.read(bytes, offset, length);
                if (read < 0)
                    throw new EOFException();
                offset += read;
                length -= read;
            }
            return new TLSRecord(type, bytes);
        }
