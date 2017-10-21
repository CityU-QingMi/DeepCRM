    private static <T extends IdentifiableBase> Map<String, T> mapById( List<T> identifiables )
    {
        Map<String, T> byId = new HashMap<>();

        for ( T identifiable : identifiables )
        {
            byId.put( identifiable.getId(), identifiable );
        }

        return byId;
    }
