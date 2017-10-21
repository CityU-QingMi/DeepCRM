        public File[] getCompleteMainFileSetList() {

            File[] fileList = new File[suffixes.length];

            for (int i = 0; i < suffixes.length; i++) {
                fileList[i] = new File(canonicalFile.getPath() + suffixes[i]);
            }

            return fileList;
        }
