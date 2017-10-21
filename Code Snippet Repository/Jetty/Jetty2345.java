        private void overflow(ByteBuffer output) throws IOException
        {
            if (outputFile == null)
            {
                Path path = Files.createTempFile(getOverflowDirectory(), getOutputFilePrefix(), null);
                outputFile = FileChannel.open(path,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.READ,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.DELETE_ON_CLOSE);
                int size = sinkBuffers.size();
                if (size > 0)
                {
                    ByteBuffer[] buffers = sinkBuffers.toArray(new ByteBuffer[size]);
                    sinkBuffers.clear();
                    
                    IO.write(outputFile,buffers,0,buffers.length);
                }
            }
            outputFile.write(output);
        }
