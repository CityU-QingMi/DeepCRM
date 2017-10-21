        static protected String getLameMode(File file) {
            int umod = 0;

//#ifdef JAVA6
            if (file.canExecute()) {
                umod = 1;
            }

//#endif
            if (file.canWrite()) {
                umod += 2;
            }

            if (file.canRead()) {
                umod += 4;
            }

            return "0" + umod + "00";

            // Conservative since Java gives us no way to determine group or
            // other privileges on a file, and this file may contain passwords.
        }
