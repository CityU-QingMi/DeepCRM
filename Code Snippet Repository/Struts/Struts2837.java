    public static String toJavaSourceType(String type) {

        if (type.charAt(0) != '[') {
            return type;
        }

        int dims = 1;
        String t = null;
        for (int i = 1; i < type.length(); i++) {
            if (type.charAt(i) == '[') {
                dims++;
            } else {
                switch (type.charAt(i)) {
                    case 'Z':
                        t = "boolean";
                        break;
                    case 'B':
                        t = "byte";
                        break;
                    case 'C':
                        t = "char";
                        break;
                    case 'D':
                        t = "double";
                        break;
                    case 'F':
                        t = "float";
                        break;
                    case 'I':
                        t = "int";
                        break;
                    case 'J':
                        t = "long";
                        break;
                    case 'S':
                        t = "short";
                        break;
                    case 'L':
                        t = type.substring(i + 1, type.indexOf(';'));
                        break;
                }
                break;
            }
        }
        StringBuilder resultType = new StringBuilder(t);
        for (; dims > 0; dims--) {
            resultType.append("[]");
        }
        return resultType.toString();
    }
