    protected void skipFileData(TarEntryHeader header)
    throws IOException, TarMalformatException {

/**/
/**/
/**/
/**/
        if (header.getDataSize() == 0) {
            return;
        }

        if (header.getDataSize() < 0) {
            throw new TarMalformatException(RB.data_size_unknown.getString());
        }

        int skipNow;
        int oddBlocks  = (header.getDataSize() % 512L == 0L) ? 0
                                                             : 1;
        int skipBlocks = (int) (header.getDataSize() / 512L) + oddBlocks;

        while (skipBlocks > 0) {
            skipNow = (skipBlocks > archive.getReadBufferBlocks())
                      ? archive.getReadBufferBlocks()
                      : skipBlocks;

            archive.readBlocks(skipNow);

            skipBlocks -= skipNow;
        }
    }
