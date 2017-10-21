    public void write() throws IOException, TarMalformatException {

        long startTime = new java.util.Date().getTime();

        checkEssentialFiles();

        TarGenerator generator = new TarGenerator(archiveFile, overWrite,
            Integer.valueOf(DbBackup.generateBufferBlockValue(componentFiles)));

        for (int i = 0; i < componentFiles.length; i++) {
            boolean exists = componentStreams[i] != null
                             || componentFiles[i].exists();

            if (!exists) {
                continue;

                // We've already verified that required files exist, therefore
                // there is no error condition here.
            }

            if (ignoreList[i]) {
                continue;
            }

            if (componentStreams[i] == null) {
                generator.queueEntry(componentFiles[i].getName(),
                                     componentFiles[i]);

                existList[i] = true;
            } else {
                generator.queueEntry(componentFiles[i].getName(),
                                     componentStreams[i]);
            }
        }

        generator.write();
        checkFilesNotChanged(startTime);
    }
