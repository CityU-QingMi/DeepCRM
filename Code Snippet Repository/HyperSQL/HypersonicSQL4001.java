    private static String translateFileExtension(String fileName, String ext) {
        if (ext != null) {

            int pos = fileName.lastIndexOf('.');

            fileName = (pos < 0) ? fileName + ext
                                 : fileName.substring(0, pos) + ext;
        }

        return fileName;
    }
