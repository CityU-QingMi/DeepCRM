    private void setupLog(File file) {

        try {
            FileUtil.getFileUtil().makeParentDirectories(file);

            writer = new PrintWriter(new FileWriter(file, true), true);
        } catch (Exception e) {
            isSystem = true;
            writer   = new PrintWriter(System.out);
        }
    }
