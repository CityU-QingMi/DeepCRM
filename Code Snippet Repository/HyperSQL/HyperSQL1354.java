    public void writeAsFiles() throws IOException {

        int bufferSize = 512
                         * DbBackup.generateBufferBlockValue(componentFiles);
        byte[] writeBuffer = new byte[bufferSize];

        checkEssentialFiles();
        FileOutputStream fileOut = null;

        for (int i = 0; i < componentFiles.length; i++) try {
            if (ignoreList[i]) {
                continue;
            }

            if (!componentFiles[i].exists()) {
                continue;
            }

            File outFile = new File(archiveFile, componentFiles[i].getName());
            fileOut = new FileOutputStream(outFile);

            if (componentStreams[i] == null) {
                componentStreams[i] = new InputStreamWrapper(
                    new FileInputStream(componentFiles[i]));
            }

            InputStreamInterface instream = componentStreams[i];

            while (true) {
                int count = instream.read(writeBuffer, 0, writeBuffer.length);

                if (count <= 0) {
                    break;
                }

                fileOut.write(writeBuffer, 0, count);
            }

            instream.close();
            fileOut.flush();
            fileOut.getFD().sync();
        } finally {
            if (fileOut != null) {
                fileOut.close();
                fileOut = null;
            }
        }
    }
