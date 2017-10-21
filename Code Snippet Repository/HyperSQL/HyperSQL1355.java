    void checkFilesNotChanged(long startTime) throws FileNotFoundException {

        // abortUponModify is used with offline invocation only
        if (!abortUponModify) {
            return;
        }

        try {
            for (int i = 0; i < componentFiles.length; i++) {
                if (componentFiles[i].exists()) {
                    if (!existList[i]) {
                        throw new FileNotFoundException(
                            RB.file_disappeared.getString(
                                componentFiles[i].getAbsolutePath()));
                    }

                    if (componentFiles[i].lastModified() > startTime) {
                        throw new FileNotFoundException(
                            RB.file_changed.getString(
                                componentFiles[i].getAbsolutePath()));
                    }
                } else if (existList[i]) {
                    throw new FileNotFoundException(
                        RB.file_appeared.getString(
                            componentFiles[i].getAbsolutePath()));
                }
            }
        } catch (IllegalStateException ise) {
            if (!archiveFile.delete()) {
                System.out.println(
                    RB.cleanup_rmfail.getString(
                        archiveFile.getAbsolutePath()));

                // Be-it-known.  This method can write to stderr if
                // abortUponModify is true.
            }

            throw ise;
        }
    }
