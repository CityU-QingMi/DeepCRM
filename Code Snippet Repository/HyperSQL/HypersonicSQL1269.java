    private void dump(File dumpFile) throws IOException, BadSpecial {
        if (binBuffer == null)
            throw new BadSpecial(SqltoolRB.binbuffer_empty.getString());

        int len = 0;
        FileOutputStream fos = new FileOutputStream(dumpFile);

        try {
            fos.write(binBuffer);

            len = binBuffer.length;

            binBuffer = null;

            fos.flush();
        } finally {
            try {
                fos.close();
            } catch (IOException ioe) {
                // Intentionally empty
            } finally {
                fos = null; // Encourage GC of buffers
            }
        }
        stdprintln(SqltoolRB.file_wrotechars.getString(
                len, dumpFile.toString()));
    }
