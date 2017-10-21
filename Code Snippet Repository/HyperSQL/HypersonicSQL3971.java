    Hashtable getSupportedTypes() {

        Hashtable hTypes = new Hashtable();

        if (db != null) {
            try {
                ResultSet result = db.meta.getTypeInfo();

                while (result.next()) {
                    Integer intobj = new Integer(result.getShort(2));

                    if (hTypes.get(intobj) == null) {
                        try {
                            int typeNumber = result.getShort(2);

                            hTypes.put(intobj, JDBCT.toString(typeNumber));
                        } catch (Exception e) {}
                    }
                }

                result.close();
            } catch (SQLException e) {}
        }

        if (hTypes.isEmpty()) {
            hTypes = JDBCT.getHashtable();
        }

        return hTypes;
    }
