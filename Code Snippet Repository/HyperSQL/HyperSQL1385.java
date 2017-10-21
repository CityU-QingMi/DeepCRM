        public File generateFile() {

            if (entryType != '\0' && entryType != '0') {
                throw new IllegalStateException(
                        RB.create_only_normal.getString());
            }

            // Unfortunately, it does no good to set modification times or
            // privileges here, since those settings have no effect on our
            // new file until after is created by the FileOutputStream
            // constructor.
            return new File(path);
        }
