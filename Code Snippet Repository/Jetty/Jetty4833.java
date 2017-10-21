    public static void readFrom(File file, ByteBuffer buffer) throws IOException
    {
        try(RandomAccessFile raf = new RandomAccessFile(file,"r"))
        {
            FileChannel channel = raf.getChannel();
            long needed=raf.length();

            while (needed>0 && buffer.hasRemaining())
                needed=needed-channel.read(buffer);
        }
    }
