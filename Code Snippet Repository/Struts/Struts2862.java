        public String[] getParameterTypeNames() {
            if (this.tai != null) {
                if (this.isDeferredMethodInput()) {
                    String m = this.tai.getMethodSignature();
                    if (m != null) {
                        m = m.trim();
                        m = m.substring(m.indexOf('(') + 1);
                        m = m.substring(0, m.length() - 1);
                        if (m.trim().length() > 0) {
                            String[] p = m.split(",");
                            for (int i = 0; i < p.length; i++) {
                                p[i] = p[i].trim();
                            }
                            return p;
                        }
                    }
                }
            }
            return new String[0];
        }
