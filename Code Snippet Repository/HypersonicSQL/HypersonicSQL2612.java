    private final long readHeartbeat()
    throws LockFile.FileSecurityException,
           LockFile.UnexpectedFileNotFoundException,
           LockFile.UnexpectedEndOfFileException,
           LockFile.UnexpectedFileIOException, LockFile.WrongMagicException {

        FileInputStream fis = null;
        DataInputStream dis = null;

        try {
            if (!file.exists()) {
                return Long.MIN_VALUE;
            }

            fis = new FileInputStream(file);
            dis = new DataInputStream(fis);

            checkMagic(dis);

            return dis.readLong();
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "readHeartbeat", ex);
        } catch (FileNotFoundException ex) {
            throw new UnexpectedFileNotFoundException(this, "readHeartbeat",
                    ex);
        } catch (EOFException ex) {
            throw new UnexpectedEndOfFileException(this, "readHeartbeat", ex);
        } catch (IOException ex) {
            throw new UnexpectedFileIOException(this, "readHeartbeat", ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ioe) {

                    // ioe.printStackTrace();
                }
            }
        }
    }
