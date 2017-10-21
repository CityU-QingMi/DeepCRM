    protected void openFile() {

        try {
            FileAccess   fa  = isUserScript ? FileUtil.getFileUtil()
                                            : database.logger.getFileAccess();
            OutputStream fos = fa.openOutputStreamElement(outFile);

            outDescriptor = fa.getFileSync(fos);
            fileStreamOut = fos;
            fileStreamOut = new BufferedOutputStream(fos, 1 << 14);
        } catch (IOException e) {
            throw Error.error(e, ErrorCode.FILE_IO_ERROR,
                              ErrorCode.M_Message_Pair, new Object[] {
                e.toString(), outFile
            });
        }
    }
