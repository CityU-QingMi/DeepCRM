    private Mirror newMirror( String id, String mirrorOf, String layouts, String url )
    {
        Mirror mirror = new Mirror();

        mirror.setId( id );
        mirror.setMirrorOf( mirrorOf );
        mirror.setMirrorOfLayouts( layouts );
        mirror.setUrl( url );

        return mirror;
    }
