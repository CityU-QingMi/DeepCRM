    static void deleteDatabase(String path) {

        FileUtil fileUtil = FileUtil.getFileUtil();

        fileUtil.delete(path + ".backup");
        fileUtil.delete(path + ".properties");
        fileUtil.delete(path + ".script");
        fileUtil.delete(path + ".data");
        fileUtil.delete(path + ".log");
        fileUtil.delete(path + ".lck");
        fileUtil.delete(path + ".csv");
    }
