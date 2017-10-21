    private File getFile(String name) {

        session.checkAdmin();

        String fileName = database.logger.getSecurePath(name, false, false);

        if (fileName == null) {
            throw Error.error(ErrorCode.ACCESS_IS_DENIED, name);
        }

        File    file   = new File(fileName);
        boolean exists = file.exists();

        if (!exists) {
            throw Error.error(ErrorCode.FILE_IO_ERROR);
        }

        return file;
    }
