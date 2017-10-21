    private StringBuffer appendParams(StringBuffer sb) {

        sb.append("PARAMETERS=[");

        for (int i = 0; i < parameters.length; i++) {
            sb.append('\n').append('@').append(i).append('[').append(
                parameters[i].describe(null, 0)).append(']');
        }

        sb.append(']');

        return sb;
    }
