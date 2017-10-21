        private void drainTo(List<ByteBuffer> output) throws IOException
        {
            if (outputFile == null)
            {
                output.addAll(sinkBuffers);
                sinkBuffers.clear();
            }
            else
            {
                outputFile.force(true);
                drain(outputFile, output);
            }
        }
