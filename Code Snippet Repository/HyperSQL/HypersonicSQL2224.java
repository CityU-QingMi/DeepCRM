    public void readBlocks(int blocks)
    throws IOException, TarMalformatException {

/**/
/**/
        if (compressionType
                != TarFileOutputStream.Compression.NO_COMPRESSION) {
            readCompressedBlocks(blocks);

            return;
        }

        int i = readStream.read(readBuffer, 0, blocks * 512);

        bytesRead += i;

        if (i != blocks * 512) {
            throw new TarMalformatException(
                RB.insufficient_read.getString(blocks * 512, i));
        }
    }
