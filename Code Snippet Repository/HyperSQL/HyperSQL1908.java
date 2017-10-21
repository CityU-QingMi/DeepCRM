    public static void restoreFile(Database database, String sourceName,
                                   String destName) throws IOException {

        RandomAccessInterface source = getStorage(database, sourceName, "r");
        RandomAccessInterface dest   = getStorage(database, destName, "rw");

        while (source.getFilePointer() != source.length()) {
            int    size     = source.readInt();
            long   position = source.readLong();
            byte[] buffer   = new byte[size];

            source.read(buffer, 0, buffer.length);
            dest.seek(position);
            dest.write(buffer, 0, buffer.length);
        }

        source.close();
        dest.synch();
        dest.close();
    }
