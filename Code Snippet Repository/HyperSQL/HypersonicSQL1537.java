    private String toStringImpl() throws Exception {

        StringBuffer sb;
        Field[]      fields;
        Field        field;

        sb = new StringBuffer();

        sb.append('[');

        fields = getClass().getFields();

        int len = fields.length;

        for (int i = 0; i < len; i++) {
            field = fields[i];

            sb.append(field.getName());
            sb.append('=');
            sb.append(field.get(this));

            if (i + 1 < len) {
                sb.append(',');
                sb.append(' ');
            }
        }
        sb.append(']');

        return sb.toString();
    }
