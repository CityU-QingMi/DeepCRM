    public HsqlName newSpecificRoutineName(HsqlName name) {

        StringBuffer sb = new StringBuffer();

        sb.append(name.name).append('_').append(sysNumber.incrementAndGet());

        HsqlName hsqlName = new HsqlName(this, sb.toString(),
                                         SchemaObject.SPECIFIC_ROUTINE,
                                         name.isNameQuoted);

        hsqlName.parent = name;
        hsqlName.schema = name.schema;

        return hsqlName;
    }
