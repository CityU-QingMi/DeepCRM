    void writeRowOutToFile() throws IOException {

        if (fileStreamOut == null) {
            return;
        }

        synchronized (fileStreamOut) {
            fileStreamOut.write(rowOut.getBuffer(), 0, rowOut.size());

            byteCount += rowOut.size();

            lineCount++;
        }
    }
