        private void initialise() {

            limitSize = synchLength;

            database.logger.logDetailEvent("shadow file size for backup: "
                                           + limitSize);

            if (limitSize > 0) {
                try {
                    is = new FileInputStream(pathName);
                } catch (FileNotFoundException e) {}
            }

            initialised = true;
        }
