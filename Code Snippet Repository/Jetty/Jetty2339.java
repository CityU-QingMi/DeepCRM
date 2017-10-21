    private void overflow(ByteBuffer input) throws IOException
    {
        if (inputFile == null)
        {
            Path path = Files.createTempFile(getOverflowDirectory(), getInputFilePrefix(), null);
            inputFile = FileChannel.open(path,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.READ,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.DELETE_ON_CLOSE);
            int size = sourceBuffers.size();
            if (size > 0)
            {
                ByteBuffer[] buffers = sourceBuffers.toArray(new ByteBuffer[size]);
                sourceBuffers.clear();
                IO.write(inputFile,buffers,0,buffers.length);
            }
        }
        inputFile.write(input);
    }
