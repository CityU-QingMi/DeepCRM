    public HsqlName newAutoName(String prefix, String namepart,
                                HsqlName schema, HsqlName parent, int type) {

        StringBuffer sb = new StringBuffer();

        if (prefix != null) {
            if (prefix.length() != 0) {
                sb.append("SYS_");
                sb.append(prefix);
                sb.append('_');

                if (namepart != null) {
                    sb.append(namepart);
                    sb.append('_');
                }

                sb.append(sysNumber.incrementAndGet());
            }
        } else {
            sb.append(namepart);
        }

        HsqlName name = new HsqlName(this, sb.toString(), type, false);

        name.schema = schema;
        name.parent = parent;

        return name;
    }
