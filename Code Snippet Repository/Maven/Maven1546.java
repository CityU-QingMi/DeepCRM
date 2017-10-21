    private ObjectWithMixedProtection getValueList()
    {
        List<String[]> values = new ArrayList<>();

        values.add( new String[] { "${key}", "${key2}" } );
        values.add( new String[] { "${key3}", "${key4}" } );
        List<String> values2 = new ArrayList<>();
        values.add( new String[] { "${key}", "${key2}" } );
        values.add( new String[] { "${key3}", "${key4}" } );
        List<String> values3 = new ArrayList<>();
        values.add( new String[] { "${key}", "${key2}" } );
        values.add( new String[] { "${key3}", "${key4}" } );

        return new ObjectWithMixedProtection( values, values2, values3, "${key5}" );
    }
