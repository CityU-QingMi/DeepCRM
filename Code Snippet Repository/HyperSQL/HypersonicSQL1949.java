        public boolean accept(File dir, String name) {

            if (parent.equals(dir) && name.indexOf(dbName) == 0) {
                String suffix = name.substring(dbName.length());

                if (extraFiles) {
                    for (int i = 0; i < extraSuffixes.length; i++) {
                        if (suffix.equals(extraSuffixes[i])) {
                            return true;
                        }
                    }
                }

                for (int i = 0; i < suffixes.length; i++) {
                    if (suffix.equals(suffixes[i])) {
                        return true;
                    }

                    if (!extraFiles) {
                        continue;
                    }

                    if (suffix.startsWith(suffixes[i])) {
                        if (name.endsWith(".new")) {
                            if (suffix.length() == suffixes[i].length() + 4) {
                                return true;
                            }
                        } else if (name.endsWith(".old")) {
                            if (suffix.length()
                                    == suffixes[i].length()
                                       + discardSuffixLength + 4) {
                                return true;
                            }
                        }
                    }
                }
            }

            return false;
        }
