    void writeRowOutToFile() throws IOException {

        synchronized (fileStreamOut) {
            if (byteOut == null) {
                fileStreamOut.write(rowOut.getBuffer(), 0, rowOut.size());

                byteCount += rowOut.size();

                lineCount++;

                return;
            }

            int count = crypto.getEncodedSize(rowOut.size());

            byteOut.ensureRoom(count + 4);

            count = crypto.encode(rowOut.getBuffer(), 0, rowOut.size(),
                                  byteOut.getBuffer(), 4);

            byteOut.setPosition(0);
            byteOut.writeInt(count);
            fileStreamOut.write(byteOut.getBuffer(), 0, count + 4);

            byteCount += rowOut.size();

            lineCount++;
        }
    }
