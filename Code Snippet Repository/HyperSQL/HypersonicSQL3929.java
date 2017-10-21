    String formatTableName(String t) {

        if (t == null) {
            return t;
        }

        if (t.equals("")) {
            return t;
        }

        if (t.indexOf(' ') != -1) {
            return ("[" + t + "]");
        } else {
            return (formatIdentifier(t));
        }
    }
