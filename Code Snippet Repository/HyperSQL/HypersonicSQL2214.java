    public void setFileIgnore(String fileExtension) {

        for (int i = 0; i < componentFiles.length; i++) {
            if (componentFiles[i].getName().endsWith(fileExtension)) {
                ignoreList[i] = true;

                break;
            }
        }
    }
