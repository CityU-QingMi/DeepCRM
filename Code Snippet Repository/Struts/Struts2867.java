        public String getText() {
            String ret = text;
            if (ret == null) {
                if (body != null) {
                    StringBuffer buf = new StringBuffer();
                    for (int i = 0; i < body.size(); i++) {
                        buf.append(body.getNode(i).getText());
                    }
                    ret = buf.toString();
                } else {
                    // Nulls cause NPEs further down the line
                    ret = "";
                }
            }
            return ret;
        }
