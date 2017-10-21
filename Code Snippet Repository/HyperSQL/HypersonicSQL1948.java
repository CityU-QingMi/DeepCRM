        public File[] getExistingMainFileSetList() {

            File[]        fileList = getCompleteMainFileSetList();
            HsqlArrayList list     = new HsqlArrayList();

            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].exists()) {
                    list.add(fileList[i]);
                }
            }

            fileList = new File[list.size()];

            list.toArray(fileList);

            return fileList;
        }
