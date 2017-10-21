    private static int regexpParams(String params) {

        int flags = 0;

        if (params == null) {
            return flags;
        }

        for (int i = 0; i < params.length(); ++i) {
            switch (params.charAt(i)) {

                case 'i' :
                    flags |= Pattern.CASE_INSENSITIVE;
                    break;

                case 'c' :
                    flags &= ~Pattern.CASE_INSENSITIVE;
                    flags |= Pattern.UNICODE_CASE;
                    break;

                case 'n' :
                    flags |= Pattern.DOTALL;
                    break;

                case 'm' :
                    flags |= Pattern.MULTILINE;
                    break;

                default :
                    throw Error.error(ErrorCode.X_22511, params);
            }
        }

        return flags;
    }
