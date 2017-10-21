    public void close() throws IOException {

        if (writeStream == null) {
            return;
        }

        try {
            writeStream.close();

            if (!writeFile.delete()) {
                throw new IOException(
                    RB.workfile_delete_fail.getString(
                        writeFile.getAbsolutePath()));
            }
        } finally {
            writeStream = null;    // Encourage buffer GC
        }
    }
