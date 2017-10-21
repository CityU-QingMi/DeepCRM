        @Override
        public int hashCode() {
            if(hashCode==0) {
                int rc= 0;
                for(final Object key : keys) {
                    if(key!=null) {
                        rc= rc*7 + key.hashCode();
                    }
                }
                hashCode= rc;
            }
            return hashCode;
        }
