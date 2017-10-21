    void writeDataType(RowOutputInterface out, Type type) {

        out.writeType(type.typeCode);

        if (type.isArrayType()) {
            out.writeType(type.collectionBaseType().typeCode);
        }

        out.writeLong(type.precision);
        out.writeInt(type.scale);
    }
