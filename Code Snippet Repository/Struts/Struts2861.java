        public String getExpectedTypeName() {
            if (this.tai != null) {
                if (this.isDeferredInput()) {
                    return this.tai.getExpectedTypeName();
                } else if (this.isDeferredMethodInput()) {
                    String m = this.tai.getMethodSignature();
                    if (m != null) {
                        int rti = m.trim().indexOf(' ');
                        if (rti > 0) {
                            return m.substring(0, rti).trim();
                        }
                    }
                }
            }
            return "java.lang.Object";
        }
