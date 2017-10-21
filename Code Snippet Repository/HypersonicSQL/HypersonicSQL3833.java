    void addDir(File f) {

        if (f.isFile() && f.getName().endsWith(".java")) {
            vList.addElement(f.getPath());
        } else if (f.isDirectory()) {
            File[] list = f.listFiles();

            for (int i = 0; i < list.length; i++) {
                addDir(list[i]);
            }
        }
    }
