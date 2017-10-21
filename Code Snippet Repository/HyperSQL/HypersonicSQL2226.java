    public boolean readNextHeaderBlock()
    throws IOException, TarMalformatException {

        // We read a-byte-at-a-time because there should only be 2 empty blocks
        // between each Tar Entry.
        try {
            while (readStream.available() > 0) {
                readBlock();

                if (readBuffer[0] != 0) {
                    return true;
                }
            }
        } catch (EOFException ee) {
/**/
/**/
/**/
/**/
/**/
/**/
/**/
        }

        close();

        return false;
    }
