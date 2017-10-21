    private String toCsvValue(String str) {

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            sb.append(c);

            switch (c) {

                case '"' :
                    sb.append('"');
                    break;
            }
        }

        return sb.toString();
    }
