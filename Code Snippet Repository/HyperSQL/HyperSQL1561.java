        public int compare(Object a, Object b) {

            long diff;

            switch (compareType) {

                case COMPARE_POSITION :
                    diff = ((CachedObject) a).getPos()
                           - ((CachedObject) b).getPos();
                    break;

                case COMPARE_SIZE :
                    diff = ((CachedObject) a).getStorageSize()
                           - ((CachedObject) b).getStorageSize();
                    break;

                default :
                    return 0;
            }

            return diff == 0 ? 0
                             : diff > 0 ? 1
                                        : -1;
        }
