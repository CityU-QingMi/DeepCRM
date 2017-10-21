    protected String guessResultType(String type) {
        StringBuilder sb = null;
        if (type != null) {
            sb = new StringBuilder();
            boolean capNext = false;
            for (int x=0; x<type.length(); x++) {
                char c = type.charAt(x);
                if (c == '-') {
                    capNext = true;
                    continue;
                } else if (Character.isLowerCase(c) && capNext) {
                    c = Character.toUpperCase(c);
                    capNext = false;
                }
                sb.append(c);
            }
        }
        return (sb != null ? sb.toString() : null);
    }
