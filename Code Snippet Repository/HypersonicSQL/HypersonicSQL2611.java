    private final void checkMagic(final DataInputStream dis)
    throws LockFile.FileSecurityException,
           LockFile.UnexpectedEndOfFileException,
           LockFile.UnexpectedFileIOException, LockFile.WrongMagicException {

        boolean      success = true;
        final byte[] magic   = new byte[MAGIC.length];

        try {
            for (int i = 0; i < MAGIC.length; i++) {
                magic[i] = dis.readByte();

                if (MAGIC[i] != magic[i]) {
                    success = false;
                }
            }
        } catch (SecurityException ex) {
            throw new FileSecurityException(this, "checkMagic", ex);
        } catch (EOFException ex) {
            throw new UnexpectedEndOfFileException(this, "checkMagic", ex);
        } catch (IOException ex) {
            throw new UnexpectedFileIOException(this, "checkMagic", ex);
        }

        if (!success) {
            throw new WrongMagicException(this, "checkMagic", magic);
        }
    }
