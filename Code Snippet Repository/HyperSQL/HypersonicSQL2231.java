    public void finish() throws IOException {

        try {
            long finalBlock = bytesWritten / 512 + 2;

            if (finalBlock % blocksPerRecord != 0) {

                // Round up total archive size to a blocksPerRecord multiple
                finalBlock = (finalBlock / blocksPerRecord + 1)
                             * blocksPerRecord;
            }

            int finalPadBlocks = (int) (finalBlock - bytesWritten / 512L);

            if (TarFileOutputStream.debug) {
                System.out.println(
                    RB.pad_block_write.getString(finalPadBlocks));
            }

            writePadBlocks(finalPadBlocks);
        } catch (IOException ioe) {
            try {
                close();
            } catch (IOException ne) {

                // Too difficult to report every single error.
                // More important that the user know about the original Exc.
            }

            throw ioe;
        }

        writeStream.close();
        writeFile.renameTo(targetFile);
    }
