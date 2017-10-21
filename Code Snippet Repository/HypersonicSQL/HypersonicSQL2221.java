    public PIFGenerator(File file) {

        this();

        //typeFlag = 'x';
        String parentPath = (file.getParentFile() == null) ? "."
                                                           : file.getParentFile()
                                                               .getPath();

        name = parentPath + "/PaxHeaders." + fakePid + '/' + file.getName();
    }
