    private void drain(FileChannel file, List<ByteBuffer> output) throws IOException
    {
        long position = 0;
        long length = file.size();
        file.position(position);
        while (length > 0)
        {
            // At most 1 GiB file maps.
            long size = Math.min(1024 * 1024 * 1024, length);
            ByteBuffer buffer = file.map(FileChannel.MapMode.READ_ONLY, position, size);
            output.add(buffer);
            position += size;
            length -= size;
        }
    }
