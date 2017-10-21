        public boolean accept(File dir, String name) {

            if (name.length() < suffixFormat.length() + 1) {

                // Require variable name length >= 1 char
                return false;
            }

            int suffixPos = name.length() - suffixFormat.length();

            // Would like to use Java 1.4's java.util.regex here.
            return name.endsWith(".tar") && name.substring(
                suffixPos, suffixPos + autoMiddlingString.length()).equals(
                autoMiddlingString);
        }
