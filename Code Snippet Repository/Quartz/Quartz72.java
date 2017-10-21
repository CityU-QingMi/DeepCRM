        public static int compare(Date nextFireTime1, int priority1, TriggerKey key1, Date nextFireTime2, int priority2, TriggerKey key2) {
            if (nextFireTime1 != null || nextFireTime2 != null) {
                if (nextFireTime1 == null) {
                    return 1;
                }

                if (nextFireTime2 == null) {
                    return -1;
                }

                if(nextFireTime1.before(nextFireTime2)) {
                    return -1;
                }

                if(nextFireTime1.after(nextFireTime2)) {
                    return 1;
                }
            }

            int comp = priority2 - priority1;
            if (comp != 0) {
                return comp;
            }

            return key1.compareTo(key2);
        }
