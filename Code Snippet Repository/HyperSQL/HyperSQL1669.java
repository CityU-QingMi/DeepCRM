        static int sysPrefixLength(String name) {

            for (int i = 0; i < sysPrefixes.length; i++) {
                if (name.startsWith(sysPrefixes[i])) {
                    return sysPrefixes[i].length();
                }
            }

            return 0;
        }
