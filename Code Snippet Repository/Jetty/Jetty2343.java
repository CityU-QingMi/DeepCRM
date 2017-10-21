        private void drainTo(List<ByteBuffer> output) throws IOException
        {
            if (inputFile == null)
            {
                output.addAll(sourceBuffers);
                sourceBuffers.clear();
            }
            else
            {
                drain(inputFile, output);
            }
        }
