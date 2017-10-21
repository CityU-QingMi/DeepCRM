        public int compareTo(Binding b2) {
            int ret = 0;
            if (isDefault) {
                ret = -1;
            } else if (b2.isDefault()) {
                ret = 1;
            } else {
                ret = alias.compareTo(b2.getAlias());
            }
            return ret;
        }
