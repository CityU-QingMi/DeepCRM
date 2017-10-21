        public boolean checkIfAttributeIsJspFragment(String name) {
            boolean result = false;

            TagAttributeInfo[] attributes = tagInfo.getAttributes();
            for (int i = 0; i < attributes.length; i++) {
                if (attributes[i].getName().equals(name)
                        && attributes[i].isFragment()) {
                    result = true;
                    break;
                }
            }

            return result;
        }
